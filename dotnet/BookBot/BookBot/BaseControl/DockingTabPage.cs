using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Reflection;

namespace Common.BaseControl
{
    public class DockingTabPage : CaptionPanel
    {
        #region Fields
        private bool _originalAutoSize;
        private DockingTab objOriginalParent;
        private bool bCanRedock = true;
        private string strBusinessName = string.Empty;
        private bool bCanClose = true;
        private bool bIsCloseMessageShown = false;
        #endregion

        #region Properties
        public bool OriginalAutoSize
        {
            get { return _originalAutoSize; }
        }

        public Control MainControl
        {
            get
            {
                return Controls[0];
            }
        }

        /// <summary>
        /// 元の ドッキングパネルです。
        /// </summary>
        public DockingTab OriginalParent
        {
            get
            {
                return objOriginalParent;
            }
            set
            {
                objOriginalParent = value;
            }
        }

        /// <summary>
        /// ドッキングをできるかを取得または設定します。
        /// </summary>
        public bool CanRedock
        {
            get
            {
                return bCanRedock;
            }
            set
            {
                bCanRedock = value;
            }
        }

        /// <summary>
        /// 閉じる時メッセージが表示したか。
        /// </summary>
        public bool IsCloseMessageShown
        {
            get
            {
                return bIsCloseMessageShown;
            }
            set
            {
                bIsCloseMessageShown = value;
            }
        }

        /// <summary>
        /// 閉じるをできるかを取得または設定します。
        /// </summary>
        public bool CanClose
        {
            get
            {
                return bCanClose;
            }
            set
            {
                bCanClose = value;
                //if (bCanClose)
                //{
                //    if (this.Parent.Parent is DockingForm)
                //    { 
                //        ((DockingForm)this.Parent.Parent)
                //    }
                //}
            }
        }

        public string BusinessName
        {
            get
            {
                return strBusinessName;
            }
            set
            {
                strBusinessName = value;
            }
        }
        //*** Add 2009/07/09 Sta K.Misu
        public bool IsClosing { get; protected set; }
        public DockingTabPage ParentTabPage { get; set; }
        private List<DockingTabPage> _ChildTabPages { get; set; }
        public List<DockingTabPage> ChildTabPages
        {
            get
            {
                if (_ChildTabPages == null)
                {
                    _ChildTabPages = new List<DockingTabPage>();
                }
                return _ChildTabPages;
            }
        }
        //*** Add 2009/07/09 End K.Misu
        #endregion

        #region Constructor
        public DockingTabPage()
        {

        }

        public DockingTabPage(string strTitle)
        {
            this.Text = strTitle;
        }
        #endregion

        #region Methods
        public void MainRemove()
        {
            MainControl.AutoSize = OriginalAutoSize;
            Controls.Remove(MainControl);
        }
        //*** Add 2009/07/09 Sta K.Misu
        /// <summary>
        /// 直系の親に指定したタブが含まれるか
        /// </summary>
        /// <param name="parent"></param>
        /// <returns></returns>
        public bool ContainsParent(DockingTabPage parent)
        {
            if (this.ParentTabPage == null)
            {
                return false;
            }

            if (object.ReferenceEquals(this.ParentTabPage, parent))
            {
                return true;
            }
            return this.ParentTabPage.ContainsParent(parent);
        }
        /// <summary>
        /// 子に指定したタブが含まれるか
        /// </summary>
        /// <param name="child"></param>
        /// <returns></returns>
        public bool ContainsChildren(DockingTabPage child)
        {
            if (this.ChildTabPages.Count == 0)
            {
                return false;
            }
            foreach (DockingTabPage item in this.ChildTabPages)
            {
                if (object.ReferenceEquals(item, child))
                {
                    return true;
                }
                if (item.ContainsChildren(child))
                {
                    return true;
                }
            }
            return false;
        }
        /// <summary>
        /// タブを閉じる。
        /// </summary>
        /// <returns></returns>
        public bool CloseTabPage()
        {
            if (!this.CanRedock)
            {
                return true;
            }

            for (int i = this.ChildTabPages.Count - 1; i >= 0; i--)
            {
                DockingTabPage item = this.ChildTabPages[i];
                if (!item.CloseTabPage())
                {
                    return false;
                }
            }
            this.ChildTabPages.Clear();
            DockingTab tab = this.Parent as DockingTab;
            if (tab != null)
            {
                tab.SelectedTabControl = this;
            }
            this.IsClosing = true;
            bool result = CloseFormsInTabPage();
            this.IsClosing = false;
            return result;
        }
        //*** Add 2009/07/09 End K.Misu
        //*** Add 2009/07/06 Sta K.Misu
        /// <summary>
        /// TabPageを閉じます。
        /// </summary>
        protected bool CloseFormsInTabPage()
        {
            foreach (Control control in this.Controls)
            {
                Form formTemp = control as Form;
                if (null != formTemp)
                {
                    bool bIsClosable = true;
                    //this.IsCloseMessageShown = false;
                    Type type = formTemp.GetType();
                    MethodInfo info = type.GetMethod("ProcessClose", BindingFlags.Instance | BindingFlags.NonPublic);
                    if (null != info)
                    {
                        bIsClosable = (bool)info.Invoke(formTemp, null);
                    }
                    //ProcessCloseがTrueならBusinessFormのMainCloseが既に処理されているので
                    //絶対にCloseしないといけない。
                    if (bIsClosable)
                    {
                        //this.IsCloseMessageShown = true;
                        formTemp.Close();
                        //this.IsCloseMessageShown = false;
                    }
                    if (!bIsClosable)
                    {
                        return false;
                    }
                }
            }
            if (this.Parent is DockingTab)
            {
                DockingTab dtpParent = this.Parent as DockingTab;
                dtpParent.SuspendLayout();
                this.SuspendLayout();
                dtpParent.RemoveTabPage(this);
                dtpParent.ResumeLayout(true);
                if (dtpParent.TabPages.Count > 0)
                {
                    DockingTabPage selectTabPage = null;
                    if (this.ParentTabPage != null && dtpParent.TabPages.Contains(this.ParentTabPage))
                    {
                        selectTabPage = this.ParentTabPage;
                    }
                    else
                    {
                        selectTabPage = dtpParent.TabPages[dtpParent.TabPages.Count - 1];
                    }
                    dtpParent.SelectedTabControl = selectTabPage;
                }
                if (this.ParentTabPage != null)
                {
                    if (this.ParentTabPage.ChildTabPages.Contains(this))
                    {
                        this.ParentTabPage.ChildTabPages.Remove(this);
                    }
                    this.ParentTabPage = null;
                }
                this.Dispose();
            }
            return true;
        }
        //*** Add 2009/07/06 End K.Misu
        #endregion

        protected override void Dispose(bool disposing)
        {
            //*** Add 2009/07/09 Sta K.Misu
            if (this.ParentTabPage != null)
            {
                if (this.ParentTabPage.ChildTabPages.Contains(this))
                {
                    this.ParentTabPage.ChildTabPages.Remove(this);
                }
                this.ParentTabPage = null;
            }
            if (this._ChildTabPages != null)
            {
                foreach (DockingTabPage item in this._ChildTabPages)
                {
                    if (item != null)
                    {
                        item.ParentTabPage = null;
                    }
                }
                this._ChildTabPages.Clear();
                this._ChildTabPages = null;
            }
            //*** Add 2009/07/09 End K.Misu

            //*** Add Sta BugFix:0004187 2009/05/20 nitta
            this.objOriginalParent = null;
            //*** Add End BugFix:0004187 2009/05/20 nitta

            base.Dispose(disposing);

            //*** Del Sta BugFix:0004187 2009/04/27 nitta
            //Disposeが無限ループするので削除
            //try
            //{
            //    if (null != this.Controls)
            //    {
            //        foreach (Control ctr in this.Controls)
            //        {
            //            if (null != ctr && !ctr.IsDisposed)
            //            {
            //                ctr.Dispose();
            //            }
            //        }
            //    }
            //}
            //catch { }
            //*** Del End BugFix:0004187 2009/04/27 nitta
        }
    }
}
