using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.Reflection;
using Common;
using Common.BaseControl;

namespace Common.BaseControl
{
    public partial class DockingForm : Form
    {
        #region Fields
        private DockingTabPage dtpWantClose = null;
        #endregion

        #region Properties

        public DockingTab DockingTab
        {
            get
            {
                return dockingTab;
            }
        }
        #endregion

        #region Constructor
        /// <summary>
        /// コンストラクタです。
        /// </summary>
        public DockingForm()
        {
            InitializeComponent();
        }
        #endregion

        #region Methods

        #region Protected Methods

        protected override void OnShown(EventArgs e)
        {
            if (string.IsNullOrEmpty(this.Text))
            {
                //this.Text = WelClientConfig.SystemName + "　" + WelClientConfig.SystemVersion + "　" + WelClientConfig.UserName;
            }


            DockingTabPage tabPage = this.dockingTab.SelectedTabControl as DockingTabPage;
            if (null != tabPage)
            {
                foreach (Control control in tabPage.Controls)
                {
                    Form formTemp = control as Form;
                    if (null != formTemp)
                    {
                        Type type = formTemp.GetType();
                        MethodInfo info = type.GetMethod("InitDockingTabTheme", BindingFlags.Instance | BindingFlags.NonPublic);
                        if (null != info)
                        {
                            info.Invoke(formTemp, new object[] { this.dockingTab });
                        }
                    }
                }
            }

            base.OnShown(e);
        }


        protected override void OnClosing(CancelEventArgs e)
        {

            if (!dockingTab.CloseTabPage())
            {
                e.Cancel = true;
            }

            base.OnClosing(e);
        }
        #endregion

        #region Private Methods

        private void dockingTab_MouseDown(object sender, MouseEventArgs e)
        {
            if (MouseButtons.Right == e.Button)
            {
                dtpWantClose = this.dockingTab.TabControlFromPos(e.Location) as DockingTabPage;

                if (null != dtpWantClose)
                {
                    this.popMenu.Items["popMenuClose"].Visible = dtpWantClose.CanRedock;
                    //this.popMenu.Items["popMenuCloseOther"].Visible = dtpWantClose.CanRedock;

                    //if (dtpWantClose.BusinessName == WelConsts.DefaultFormName.WelTreeMenu)
                    //{
                    //    this.popMenu.Items["popMenuCloseOther"].Visible = true;
                    //}
                    //else
                    //{
                    //    this.popMenu.Items["popMenuCloseOther"].Visible = dtpWantClose.CanRedock;
                    //}
                    this.popMenu.Items["popMenuClose"].Enabled = dtpWantClose.CanClose;
                    this.popMenu.Items["popMenuCloseOther"].Enabled = dtpWantClose.CanClose;
                    dockingTab.ContextMenuStrip = this.popMenu;
                }
            }
        }


        private void dockingTab_MouseUp(object sender, MouseEventArgs e)
        {
            dockingTab.ContextMenuStrip = null;
        }


        private void popMenuClose_Click(object sender, EventArgs e)
        {
            if (null != dtpWantClose && dtpWantClose.CanRedock)
            {

                if (dtpWantClose.CloseTabPage())
                {
                    if (0 == dockingTab.TabPages.Count)
                    {
                        this.Close();
                    }
                }

            }
        }


        private void popMenuCloseOther_Click(object sender, EventArgs e)
        {
            dockingTab.CloseTabPage(dtpWantClose);
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void popMenuExpandeAll_Click(object sender, EventArgs e)
        {
            if (dockingTab.IsTab)
            {
                dockingTab.IsTab = false;
                this.popMenu.Items["popMenuOpenAllPage"].Visible = true;
                this.popMenu.Items["popMenuCloseAllPage"].Visible = true;
                this.popMenu.Items["popMenuExpandeAll"].Text = "Expande All";
                foreach (Control c in dockingTab.Controls)
                {
                    DockingTabPage captionPanel = c as DockingTabPage;
                    if (null != captionPanel)
                    {
                        captionPanel.MouseDown += new MouseEventHandler(captionPanel_MouseDown);
                        captionPanel.MouseUp += new MouseEventHandler(captionPanel_MouseUp);
                    }
                }
            }
            else
            {
                dockingTab.IsTab = true;
                this.popMenu.Items["popMenuOpenAllPage"].Visible = false;
                this.popMenu.Items["popMenuCloseAllPage"].Visible = false;
                this.popMenu.Items["popMenuExpandeAll"].Text = "Expande All";
            }
        }

        private void popMenuOpenAllPage_Click(object sender, EventArgs e)
        {
            foreach (Control c in dockingTab.Controls)
            {
                DockingTabPage captionPanel = c as DockingTabPage;
                captionPanel.FolderOpened = true;
            }
        }

        private void popMenuCloseAllPage_Click(object sender, EventArgs e)
        {
            foreach (Control c in dockingTab.Controls)
            {
                DockingTabPage captionPanel = c as DockingTabPage;
                captionPanel.FolderOpened = false;
            }
        }

        private void captionPanel_MouseUp(object sender, MouseEventArgs e)
        {
            (sender as CaptionPanel).ContextMenuStrip = null;
        }

        private void captionPanel_MouseDown(object sender, MouseEventArgs e)
        {
            if (MouseButtons.Right == e.Button)
            {
                dtpWantClose = sender as DockingTabPage;

                if (null != dtpWantClose)
                {
                    this.popMenu.Items["popMenuClose"].Visible = dtpWantClose.CanRedock;
                    this.popMenu.Items["popMenuCloseOther"].Visible = dtpWantClose.CanRedock;
                    dtpWantClose.ContextMenuStrip = this.popMenu;
                }
            }
        }

        private void dockingTab_ControlAdded(object sender, ControlEventArgs e)
        {
            DockingTabPage captionPanel = e.Control as DockingTabPage;
            if (null != captionPanel)
            {
                captionPanel.MouseDown += new MouseEventHandler(captionPanel_MouseDown);
                captionPanel.MouseUp += new MouseEventHandler(captionPanel_MouseUp);
            }
        }

        private void dockingTab_ControlRemoved(object sender, ControlEventArgs e)
        {
            DockingTabPage captionPanel = e.Control as DockingTabPage;
            if (null != captionPanel)
            {
                captionPanel.MouseDown -= new MouseEventHandler(captionPanel_MouseDown);
                captionPanel.MouseUp -= new MouseEventHandler(captionPanel_MouseUp);
            }
        }

        private void popMenu_PreviewKeyDown(object sender, PreviewKeyDownEventArgs e)
        {
            ToolStripMenuItem tsmItem = null;
            foreach (ToolStripMenuItem item in popMenu.Items)
            {
                if (item.Text.Contains(e.KeyData.ToString()))
                {
                    tsmItem = item;
                }
            }
            if (null != tsmItem)
            {
                tsmItem.PerformClick();
            }
        }
        #endregion

        #endregion

    }
}