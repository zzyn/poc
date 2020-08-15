using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Drawing;

namespace Common.BaseControl
{
    public class DockingTab : TabPanel
    {
        #region Fields
        private List<DockingTabPage> listTabPages = new List<DockingTabPage>();
        private int nTabCount = 0;
        private int nLocationX = 10;
        private int nOpenHeight = 800;
        private int nClosedHeight = 20;
        private int nScrollBarWith = 30;
        private int nCurrentRemovedPageNo = -1;
        #endregion

        #region Properties
        public List<DockingTabPage> TabPages
        {
            get
            {
                return listTabPages;
            }
        }

        public int TabCount
        {
            get
            {
                return nTabCount;
            }
        }
        #endregion

        #region Constructor
        public DockingTab()
        {
            this.AllowDrop = true;
        }
        #endregion

        #region Methods

        #region Public Methods
        public void OpenNewTabPage(DockingTabPage tp)
        {
            DockingForm df = new DockingForm();
            df.Size = new Size(this.Size.Width + SystemInformation.Border3DSize.Width * 2,
                this.Size.Height + SystemInformation.CaptionHeight + SystemInformation.Border3DSize.Height * 2);
            df.DockingTab.ReDockTabPage(tp);
            df.Text = tp.Text;
            df.PerformLayout();
            df.Show();
        }

        public void OpenNewTabPageAsDialog(DockingTabPage tp)
        {
            DockingForm df = new DockingForm();
            df.Size = new Size(this.Size.Width + SystemInformation.Border3DSize.Width * 2,
                this.Size.Height + SystemInformation.CaptionHeight + SystemInformation.Border3DSize.Height * 2);
            df.DockingTab.ReDockTabPage(tp);
            df.Text = tp.Text;
            df.PerformLayout();
            df.ShowDialog();
        }

        public void ReDockTabPage(DockingTabPage tp)
        {
            if (tp.Parent is DockingTab)
            {
                DockingTab tabPanel = tp.Parent as DockingTab;
                //移動
                tabPanel.RemoveTabPage(tp);

                Controls.Add(tp);
                SelectedTabControl = tp;
            }
        }

        public void RemoveTabPage(DockingTabPage tp)
        {
            for (int i = 0; i < this.Controls.Count; i++)
            {
                if (object.Equals(tp, this.Controls[i]))
                {
                    nCurrentRemovedPageNo = i;
                    //*** Add Sta BugFix:0004187 2009/04/27 nitta
                    //無駄にループしているので追加
                    break;
                    //*** Add End BugFix:0004187 2009/04/27 nitta
                }
            }

            //*** Add Sta BugFix:0004187 2009/04/27 nitta
            tp.AutoScroll = false;
            //レイアウト編集のイベントが発生するため停止
            this.SuspendLayout();
            tp.SuspendLayout();
            tp.Hide();
            //*** Add End BugFix:0004187 2009/04/27 nitta

            Controls.Remove(tp);
            nCurrentRemovedPageNo = -1;

            //*** Add Sta BugFix:0004187 2009/04/27 nitta
            tp.ResumeLayout(true);
            this.ResumeLayout(true);
            //*** Add End BugFix:0004187 2009/04/27 nitta
        }
        //*** Add 2009/07/09 Sta K.Misu
        /// <summary>
        /// タブを閉じる。
        /// </summary>
        /// <returns></returns>
        public bool CloseTabPage()
        {
            return CloseTabPage(null);
        }
        /// <summary>
        /// タブを閉じる。
        /// </summary>
        /// <param name="cancelTabPage">キャンセルするタブ</param>
        /// <returns></returns>
        public bool CloseTabPage(DockingTabPage cancelTabPage)
        {
            for (int i = this.TabPages.Count - 1; i >= 0; i--)
            {
                DockingTabPage item = this.TabPages[i];
                if (cancelTabPage != null)
                {
                    //キャンセル対象ならスキップ
                    if (object.ReferenceEquals(item, cancelTabPage))
                    {
                        continue;
                    }
                    //子にキャンセル対象があればスキップ
                    else if (item.ContainsChildren(cancelTabPage))
                    {
                        continue;
                    }
                }
                if (!item.CloseTabPage())
                {
                    return false;
                }
            }
            if (cancelTabPage != null)
            {
                this.SelectedTabControl = cancelTabPage;
            }
            return true;
        }
        //*** Add 2009/07/09 End K.Misu
        #endregion

        #region Protected Methods
        protected override void OnControlAdded(ControlEventArgs e)
        {
            //コントロールの追加
            base.OnControlAdded(e);
            DockingTabPage tpCurrent = e.Control as DockingTabPage;

            if (null != tpCurrent)
            {
                listTabPages.Add(tpCurrent);
                nTabCount++;

                if (!_isTab && 1 < this.Controls.Count)
                {
                    DockingTabPage tpPrevious = null;
                    if (this.Controls.Count == 2)
                    {
                        tpPrevious = this.Controls[0] as DockingTabPage;
                    }
                    else
                    {
                        tpPrevious = this.Controls[this.Controls.Count - 2] as DockingTabPage;
                    }
                    int nLocationY = tpPrevious.Location.Y;
                    nLocationY = tpPrevious.FolderOpened ? (nLocationY + nOpenHeight) : (nLocationY + nClosedHeight);
                    tpCurrent.Location = new Point(nLocationX, nLocationY);
                    tpCurrent.Focus();
                }
            }
        }

        protected override void OnControlRemoved(ControlEventArgs e)
        {
            base.OnControlRemoved(e);

            DockingTabPage tp = e.Control as DockingTabPage;
            if (null != tp)
            {
                listTabPages.Remove(tp);
                nTabCount--;

                if (!_isTab && 0 < this.Controls.Count)
                {
                    ResetPageLocationWhenRemoved(tp);
                }
                SetTabPageFocus(tp);
            }
        }

        private void ResetPageLocationWhenRemoved(DockingTabPage currentProcessPage)
        {
            int nLocationY = 0;

            if (null != currentProcessPage && -1 != nCurrentRemovedPageNo)
            {
                nLocationY = this.Controls[0].Location.Y;
                if (0 == nCurrentRemovedPageNo)
                {
                    nLocationY = currentProcessPage.FolderOpened ? nLocationY - nOpenHeight : nLocationY - nClosedHeight;
                }
            }

            this.SuspendLayout();
            for (int i = 0; i < this.Controls.Count; i++)
            {
                DockingTabPage tpTemp = this.Controls[i] as DockingTabPage;
                if (null != tpTemp)
                {
                    tpTemp.Width = this.Width - nScrollBarWith;
                    tpTemp.Height = tpTemp.FolderOpened ? nOpenHeight : nClosedHeight;
                    tpTemp.Location = new Point(nLocationX, nLocationY);
                    nLocationY = tpTemp.FolderOpened ? (nLocationY + nOpenHeight) : (nLocationY + nClosedHeight);
                }
            }
            this.ResumeLayout(false);
            this.PerformLayout();
            this.Invalidate();
        }

        private void SetTabPageFocus(DockingTabPage currentProcessPage)
        {
            if (TabCount == 0)
            {
                if (Parent is DockingForm)
                {
                    ((DockingForm)Parent).Hide();
                    return;
                }
            }

            if (0 < TabCount && null != currentProcessPage)
            {
                if (-1 == nCurrentRemovedPageNo)
                {
                    this.SelectedTabControl = currentProcessPage;
                }
                else
                {
                    if (nCurrentRemovedPageNo == this.Controls.Count && 0 < nCurrentRemovedPageNo)
                    {
                        this.SelectedTabControl = this.Controls[this.Controls.Count - 1];
                    }
                    else
                    {
                        this.SelectedTabControl = this.Controls[nCurrentRemovedPageNo];
                    }
                }
            }
        }

        protected override void OnMouseMove(MouseEventArgs e)
        {
            base.OnMouseMove(e);

            if (!DesignMode)
            {
                if (e.Button == MouseButtons.Left)
                {
                    DockingTabPage tp = this.SelectedTabControl as DockingTabPage;
                    if (null != tp)
                    {
                        if (tp.CanRedock)
                        {
                            this.DoDragDrop(this.SelectedTabControl, DragDropEffects.Move);
                        }
                    }
                }
            }
        }

        protected override void OnMouseDoubleClick(MouseEventArgs e)
        {
            //新規Window
            if (!DesignMode && MouseButtons.Left == e.Button)
            {
                DockingTabPage tp = TabControlFromPos(e.Location) as DockingTabPage;
                if (null != tp && !IsMouseOnArrow)
                {
                    //親が自動生成ドッキングフォームで最後の一枚の場合分離せず
                    //if (Parent is DockingForm)
                    //{
                    //    // dblclick時の動作は、切り離しのみに統一 Redockは行わない
                    //    //if (tp.OriginalParent != null && (Parent as Form).Modal == false)
                    //    //{
                    //    //    tp.OriginalParent.ReDockTabPage(tp);
                    //    //}
                    //}
                    if (tp.CanRedock && 1 < this.TabCount)
                    {
                        OpenNewTabPage(tp);
                    }

                    else if (null != tp && tp.Controls.Count > 0 && null != tp.Controls[0] && tp.Controls[0] is Form)
                    {
                        tp.Controls[0].Focus();
                    }
                }
            }
        }

        /// <summary>
        /// ParentChangedイベントを処理関数です。
        /// </summary>
        /// <param name="e">イベントパラメータです。</param>
        protected override void OnParentChanged(EventArgs e)
        {
            base.OnParentChanged(e);
            if (!DesignMode)
            {
                Parent.AllowDrop = true;
                Parent.DragDrop += new DragEventHandler(ParentDragDrop);
                Parent.DragOver += new DragEventHandler(ParentDragOver);
            }
        }

        /// <summary>
        /// DragOverイベントを処理関数です。
        /// </summary>
        /// <param name="drgevent">イベントパラメータです。</param>
        protected override void OnDragOver(DragEventArgs drgevent)
        {
            if (drgevent.Data.GetFormats().Length == 1)
            {
                DockingTabPage tp = drgevent.Data.GetData(drgevent.Data.GetFormats()[0]) as DockingTabPage;
                if (null != tp)
                {
                    if (tp.CanRedock && tp.Parent is DockingTab && tp.Parent != this)
                    {
                        drgevent.Effect = drgevent.AllowedEffect;
                    }
                }
            }

            base.OnDragOver(drgevent);
        }

        /// <summary>
        /// DragDropイベントを処理関数です。
        /// </summary>
        /// <param name="drgevent">イベントパラメータです。</param>
        protected override void OnDragDrop(DragEventArgs drgevent)
        {
            if (drgevent.Data.GetFormats().Length == 1)
            {
                DockingTabPage tp = drgevent.Data.GetData(drgevent.Data.GetFormats()[0]) as DockingTabPage;
                if (null != tp)
                {
                    if (tp.CanRedock && tp.Parent is DockingTab && tp.Parent != this)
                    {
                        //移動
                        ReDockTabPage(tp);
                    }
                }
            }
        }

        public override bool IsTab
        {
            get
            {
                return base.IsTab;
            }
            set
            {
                base.IsTab = value;
                if (!value)
                {
                    DockingTabPage tpFocus = null;
                    foreach (Control c in Controls)
                    {
                        DockingTabPage tpTemp = c as DockingTabPage;
                        if (null != tpTemp && tpTemp.FolderOpened)
                        {
                            tpFocus = tpTemp;
                        }

                    }
                    ResetPageLocationWhenRemoved(tpFocus);
                    SetTabPageFocus(tpFocus);
                    this.AutoScroll = true;
                }
                else
                {
                    this.AutoScroll = false;
                }
            }
        }

        protected override void OnSizeChanged(EventArgs e)
        {
            base.OnSizeChanged(e);
            foreach (Control c in Controls)
            {
                c.Width = this.Width - nScrollBarWith;
            }
        }

        protected override void TabSetting(Control c)
        {
            if (IsTab)
            {
                c.Visible = false;
                c.Dock = DockStyle.Fill;
                CaptionBasePanel cp = c as CaptionBasePanel;
                if (cp != null)
                {
                    cp.TextVisible = false;
                    cp.FolderOpened = true;
                    cp.FolderOpenedChanged -= new EventHandler<EventArgs>(cp_FolderOpenedChanged);
                    DockingTabPage it = c as DockingTabPage;
                    if (it != null)
                    {
                        it.MainControl.AutoSize = false;
                    }
                }
            }
            else
            {
                CaptionBasePanel cp = c as CaptionBasePanel;
                if (cp != null)
                {
                    cp.TextVisible = true;
                    cp.FolderOpened = false;
                    cp.FolderOpenedChanged += new EventHandler<EventArgs>(cp_FolderOpenedChanged);
                    DockingTabPage it = c as DockingTabPage;
                    if (it != null)
                    {
                        it.MainControl.AutoSize = false;
                    }
                }
                c.Dock = DockStyle.None;
                if (c.Enabled)
                {
                    c.Visible = true;
                }
                else
                {
                    c.Visible = false;
                }
            }
        }

        private void cp_FolderOpenedChanged(object sender, EventArgs e)
        {
            if (!_isTab)
            {
                DockingTabPage tpCurrent = sender as DockingTabPage;
                int nCurrentNo = -1;

                if (tpCurrent.FolderOpened)
                {
                    tpCurrent.Height = nOpenHeight;
                }
                else
                {
                    tpCurrent.Height = nClosedHeight;
                }
                tpCurrent.Width = this.Width - nScrollBarWith;

                for (int i = 0; i < this.Controls.Count; i++)
                {
                    if (object.Equals(tpCurrent, this.Controls[i]))
                    {
                        nCurrentNo = i;
                    }
                    if (-1 != nCurrentNo && i > nCurrentNo)
                    {
                        if (tpCurrent.FolderOpened)
                        {
                            this.Controls[i].Location = new Point(nLocationX,
                                this.Controls[i].Location.Y + nOpenHeight - nClosedHeight);
                        }
                        else
                        {
                            this.Controls[i].Location = new Point(nLocationX,
                                this.Controls[i].Location.Y - nOpenHeight + nClosedHeight);
                        }
                    }
                }
            }
        }

        #endregion

        #region Private Methods
        /// <summary>
        /// ParentDragDropの処理関数です。
        /// </summary>
        /// <param name="sender">イベントが発生するオブジェクトです。</param>
        /// <param name="drgevent">イベントパラメータです。</param>
        private void ParentDragDrop(object sender, DragEventArgs drgevent)
        {
            if (this.TabCount == 0)
            {
                Point p = this.PointToClient(new Point(drgevent.X, drgevent.Y));
                Rectangle r = new Rectangle(new Point(0, 0), this.Size);
                if (r.Contains(p))
                {
                    OnDragDrop(drgevent);
                }
            }
        }

        /// <summary>
        /// ParentDragOverの処理関数です。
        /// </summary>
        /// <param name="sender">イベントが発生するオブジェクトです。</param>
        /// <param name="drgevent">イベントパラメータです。</param>
        private void ParentDragOver(object sender, DragEventArgs drgevent)
        {
            if (this.TabCount == 0)
            {
                Point p = this.PointToClient(new Point(drgevent.X, drgevent.Y));
                Rectangle r = new Rectangle(new Point(0, 0), this.Size);
                if (r.Contains(p))
                {
                    OnDragOver(drgevent);
                }
                else
                {
                    drgevent.Effect = DragDropEffects.None;
                }
            }
        }
        #endregion

        #endregion
    }
}
