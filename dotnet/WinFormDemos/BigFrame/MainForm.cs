using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading;
using System.Windows.Forms;
using Common.BaseControl;
using Common.BaseUI;
using Common.CustomControl;
using Mono.Cecil;
using Mono.Cecil.Cil;
using Mono.Collections.Generic;
using System.IO;


namespace BigFrame
{
    public partial class MainForm : BaseForm
    {
        public MainForm()
        {
            
            InitializeComponent();

            this.Text = "AppStore";
            this.TopLevel = true;
            
            NodeClickHandler = new MouseEventHandler(MainControl_MouseClick);
        }

        #region Fields

        private DockingTabPage dtpWantClose = null;
        private bool bDockFlag = false;
        private FavoriteInfoForm favoriteInfo = null;
        private OperatorInfoForm operatorInfo = null;
        private HistoryInfoForm historyInfo = null;
        private MouseEventHandler NodeClickHandler = null;
        private FavorateMenuArgs objArgs = null;
        private bool bIsSingleClickStart = false;
        private TreeMenuForm trMenu = null;
        

        #endregion

        #region Properties
        
        public ContextMenuStrip NodeMenu
        { get; set; }
        #endregion

        #region Events

        private void MainForm_Load(object sender, EventArgs e)
        {
            try
            {
                InitPlatFormCommon();

                OpenDefaultForm();
                
            }
            catch (Exception ex)
            {
               
               this.Close();
            }
        }

        private void MainControl_MouseUp(object sender, MouseEventArgs e)
        {
            if (MouseButtons.Right == (e.Button & MouseButtons.Right))
            {
                if (null == NodeMenu)
                {
                    NodeMenu = new ContextMenuStrip();
                    ToolStripMenuItem objOpen = new ToolStripMenuItem("Open", null, FavorateMenuOpenClick, "Open");
                    ToolStripSeparator objLine = new ToolStripSeparator();
                    ToolStripMenuItem objDel = new ToolStripMenuItem("Delete", null, FavorateMenuDeleteClick, "Delete");
                    NodeMenu.Items.AddRange(new ToolStripItem[] { objOpen, objLine, objDel });
                }
                TreeNode objNode = (sender as CmTreeView).GetNodeAt(e.Location);
                if (null != objNode)
                {
                    objArgs = new FavorateMenuArgs { Tree = sender as CmTreeView, Node = objNode };
                    objNode.ContextMenuStrip = NodeMenu;
                    objNode.ContextMenuStrip.Closed += new ToolStripDropDownClosedEventHandler(ContextMenuStrip_Closed);
                    toolFormTimer.Stop();
                    objNode.ContextMenuStrip.Show(sender as Control, e.Location);
                }
            }
        }

        private void MainControl_MouseDoubleClick(object sender, MouseEventArgs e)
        {
            CmTreeView tempTreeView = sender as CmTreeView;
            if (null != tempTreeView)
            {
                TreeNode selectedNode = tempTreeView.GetNodeAt(e.X, e.Y);
                OpenNewForm(tempTreeView, selectedNode);
            }
        }

        private void MainControl_MouseClick(object sender, MouseEventArgs e)
        {
            if (bIsSingleClickStart && MouseButtons.Left == (e.Button & MouseButtons.Left))
            {
                MainControl_MouseDoubleClick(sender, e);
            }
        }

        private void ContextMenuStrip_Closed(object sender, ToolStripDropDownClosedEventArgs e)
        {
            if (e.CloseReason != ToolStripDropDownCloseReason.ItemClicked)
            {
                toolFormTimer.Start();
            }
        }

        private void FavorateMenuOpenClick(object sender, EventArgs e)
        {
            if (null != objArgs)
            {
                this.OpenNewForm(objArgs.Tree, objArgs.Node);
                objArgs = null;
                toolFormTimer.Start();
            }
        }

        private void FavorateMenuDeleteClick(object sender, EventArgs e)
        {
            if (null != objArgs)
            {
                //favoriteInfo.DeleteFavorate(objArgs.Tree[objArgs.Node]["businessid"].ToString(), objArgs.Tree[objArgs.Node]["businessname"].ToString());
                objArgs = null;
                toolFormTimer.Start();
            }
        }

        private void btnFavoriteInfo_Click(object sender, EventArgs e)
        {
            if (bDockFlag && splitContainer.SplitterDistance > 1 && favoriteInfo.Visible)
            {
                HideToolForm();
                return;
            }
            else
            {
                HideToolForm();
            }
            ShowToolForm(favoriteInfo);
        }

        private void btnOperatorInfo_Click(object sender, EventArgs e)
        {
            if (bDockFlag && splitContainer.SplitterDistance > 1 && operatorInfo.Visible)
            {
                HideToolForm();
                return;
            }
            else
            {
                HideToolForm();
            }
            ShowToolForm(operatorInfo);
        }

        private void btnHistoryInfo_Click(object sender, EventArgs e)
        {
            if (bDockFlag && splitContainer.SplitterDistance > 1 && historyInfo.Visible)
            {
                HideToolForm();
                return;
            }
            else
            {
                HideToolForm();
            }
            ShowToolForm(historyInfo);
        }

        private void picDockFlag_Click(object sender, EventArgs e)
        {
            bDockFlag = !bDockFlag;
            if (bDockFlag)
            {
                picDockFlag.Image = Common.Properties.Resources.dock_stop;
            }
            else
            {
                picDockFlag.Image = Common.Properties.Resources.dock_flat;
            }
            HideToolForm();
        }

        private void toolFormTimer_Tick(object sender, EventArgs e)
        {
            if (!splitterLeftMenu.ClientRectangle.Contains(splitterLeftMenu.PointToClient(MousePosition))
                && !favoriteInfo.ClientRectangle.Contains(favoriteInfo.PointToClient(MousePosition))
                && !operatorInfo.ClientRectangle.Contains(operatorInfo.PointToClient(MousePosition))
                && !historyInfo.ClientRectangle.Contains(historyInfo.PointToClient(MousePosition)))
            {
                HideToolForm();
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

        private void popMenuClose_Click(object sender, EventArgs e)
        {
            if (null != dtpWantClose && dtpWantClose.CanRedock)
            {
                if (dtpWantClose.CloseTabPage())
                {
                    if (0 == dockingTab.TabPages.Count)
                    {
                        this.dockingTab.SelectedTabControl = trMenu;
                    }
                }
            }
        }

        private void popMenuCloseOther_Click(object sender, EventArgs e)
        {
            dockingTab.CloseTabPage(dtpWantClose);
        }

        private void popMenuCloseAllPage_Click(object sender, EventArgs e)
        {
            this.popMenuCloseOther_Click(sender, e);
            this.popMenuClose_Click(sender, e);
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

        private void dockingTab_MouseDown(object sender, MouseEventArgs e)
        {
            if (MouseButtons.Right == e.Button)
            {
                dtpWantClose = this.dockingTab.TabControlFromPos(e.Location) as DockingTabPage;

                if (null != dtpWantClose)
                {
                    this.popMenu.Items["popMenuClose"].Visible = dtpWantClose.CanRedock;
                    this.popMenu.Items["popMenuCloseOther"].Visible = dtpWantClose.CanRedock;
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

        private void captionPanel_MouseUp(object sender, MouseEventArgs e)
        {
            (sender as DockingTabPage).ContextMenuStrip = null;
        }

        private void captionPanel_MouseDown(object sender, MouseEventArgs e)
        {
            if (MouseButtons.Right == e.Button)
            {
                dtpWantClose = sender as DockingTabPage;

                if (null != dtpWantClose)
                {
                    this.popMenu.Items["popMenuClose"].Visible = dtpWantClose.CanRedock;
                    dtpWantClose.ContextMenuStrip = this.popMenu;
                }
            }
        }

        #endregion

        #region Method

        private Form GetAssemblyMainForm(string AssemblyPath)
        {

            Form rtnForm = null;
            Assembly assembly = Assembly.LoadFile(AssemblyPath);
            AssemblyDefinition ad = AssemblyDefinition.ReadAssembly(AssemblyPath);

            TypeDefinition tdMainForm = null;

            foreach (TypeDefinition type in ad.MainModule.Types)
            {
                if (type.IsClass
                    && type.BaseType != null
                    && 
                     ( type.BaseType.FullName == typeof(Form).FullName
                      || assembly.GetType(type.FullName).IsSubclassOf(typeof(Form))
                      )
                    )
                {
                    if (ad.EntryPoint != null)
                    {
                        Collection<Instruction> mainCode = ad.EntryPoint.Body.Method.Body.Instructions;

                        foreach (Instruction IL in mainCode)
                        {
                            if (IL.OpCode.Name == "newobj"
                                && IL.Operand != null
                                && IL.Operand.ToString().Contains(type.FullName))
                            {
                                tdMainForm = type;
                            }
                        }
                    }
                    else
                    {
                        if (type.Name == "MainForm")
                        {
                            tdMainForm = type;
                        }
                    }
                }
            }

            if (tdMainForm != null)
            {
                
                rtnForm = assembly.CreateInstance(tdMainForm.FullName) as Form;
            }

            return rtnForm;
        }

        

        private void ShowToolForm(Form objToolForm)
        {
            objToolForm.Visible = true;
            (objToolForm as ILeftToolForm).InitMainControl();
            if (bDockFlag)
            {
                objToolForm.Height = splitterToolForm.Height;
                splitContainer.Panel1.Controls.Clear();
                splitContainer.Panel1.Controls.Add(objToolForm);
                splitContainer.SplitterDistance = 260;
                splitContainer.Refresh();
            }
            else
            {
                objToolForm.Height = splitterToolForm.Height;
                splitterToolForm.Controls.Clear();
                splitterToolForm.Controls.Add(objToolForm);
                splitterToolForm.BringToFront();

                for (int i = 0; i <= 10; i++)
                {
                    splitterToolForm.Width = i * 26;
                    splitterToolForm.Refresh();
                }
            }

            (objToolForm as Form).Show();

            if (!bDockFlag)
            {
                toolFormTimer.Start();
            }
        }

        private void HideToolForm()
        {
            if (0 != splitContainer.SplitterDistance)
            {
                splitContainer.SplitterDistance = 0;
                splitContainer.Refresh();
            }
            foreach (Control c in splitContainer.Panel1.Controls)
            {
                if (c is Form)
                {
                    (c as Form).Visible = false;
                }
            }
            splitContainer.Panel1.Controls.Clear();

            if (1 != splitterToolForm.Width)
            {
                splitterToolForm.Width = 1;
                splitterToolForm.Refresh();
            }
            foreach (Control c in splitterToolForm.Controls)
            {
                if (c is Form)
                {
                    (c as Form).Visible = false;
                }
            }
            splitterToolForm.Controls.Clear();

            toolFormTimer.Stop();
        }

        private void SetLeftButtonStatus(bool bEnabled)
        {
            picDockFlag.Enabled = bEnabled;
            btnFavoriteInfo.Enabled = bEnabled;
            btnOperatorInfo.Enabled = bEnabled;
            btnHistoryInfo.Enabled = bEnabled;
        }

        private void EndSystem()
        {
            Application.Exit();
        }

        private void OpenDefaultForm()
        {

            trMenu = new TreeMenuForm(this.dockingTab);
            trMenu.TopLevel = false;
            trMenu.FormBorderStyle = FormBorderStyle.None;
            trMenu.BackColor = Color.White;
            trMenu.Dock = DockStyle.Fill;

            this.dockingTab.Controls.Add(trMenu);


            DefaultForm dForm = new DefaultForm();
            dForm.TopLevel = false;
            dForm.FormBorderStyle = FormBorderStyle.None;
            dForm.BackColor = Color.White;
            dForm.Dock = DockStyle.Fill;

            DockingTabPage dPage = new DockingTabPage(dForm.Text);

            dPage.Controls.Add(dForm);
            this.dockingTab.Controls.Add(dPage);

            this.dockingTab.SelectedTabControl = trMenu;

            

            
            
        }

        private static bool IsBusinessDebugMode()
        {
            return (Environment.CurrentDirectory.ToString() == ClientConfig.BusinessPath);
        }

        private void OpenNewForm(CmTreeView objTree, TreeNode objNode)
        {
            if (null == objTree || null == objNode)
            {
                return;
            }
            //if (RunMode.Run == currentRunMode && null != formTreeMenu)
            //{
            //    formTreeMenu.OpenNewForm(objTree[objNode]["businessid"].ToString(), true);
            //}
            //else if (RunMode.Debug == currentRunMode && null != formDebugEntry)
            //{
            //    formDebugEntry.OpenNewForm(objTree[objNode]["businessid"].ToString(), true);
            //}
        }

        protected internal void InitPlatFormCommon()
        {
           
            SetLeftButtonStatus(true);
            
            favoriteInfo = new FavoriteInfoForm();
            operatorInfo = new OperatorInfoForm();
            historyInfo = new HistoryInfoForm();
            
            //(favoriteInfo as ILeftToolForm).MainControl.MouseClick += NodeClickHandler;
            //(favoriteInfo as ILeftToolForm).MainControl.MouseDoubleClick += new MouseEventHandler(MainControl_MouseDoubleClick);
            //(favoriteInfo as ILeftToolForm).MainControl.MouseUp += new MouseEventHandler(MainControl_MouseUp);
            //(historyInfo as ILeftToolForm).MainControl.MouseClick += NodeClickHandler;
            //(historyInfo as ILeftToolForm).MainControl.MouseDoubleClick += new MouseEventHandler(MainControl_MouseDoubleClick);

            picDockFlag.Image = Common.Properties.Resources.dock_flat;
            
        }

        #endregion
    }

    internal class FavorateMenuArgs : EventArgs
    {        
        public CmTreeView Tree { get; set; }

        public TreeNode Node { get; set; }
    }

    internal interface ILeftToolForm
    {
        CmTreeView MainControl { get; }

        void InitMainControl();
    }
}
