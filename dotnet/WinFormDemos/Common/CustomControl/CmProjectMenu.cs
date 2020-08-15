using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;
using System.Windows.Forms;
using System.Drawing;
using System.ComponentModel;
using System.Collections;
using System.Drawing.Drawing2D;
using Common.BaseControl;

namespace Common.CustomControl
{
    public class CmProjectMenu : CmMenuList
    {

        #region Field

        DataSet CustomersDataSet = new DataSet();
        List<string> CommentList = new List<string>();
        string subsyscd = string.Empty;
        Padding itempadding = new Padding(6, 3, 3, 3);
        Padding slitmargin = new Padding(5, 6, 5, 6);
        Color itemcolor = Color.Empty;
        Color commentcolor = Color.Empty;
        int projectnamewidth = 100;
        int hoverindex = -1;
        bool bIsSingleClickUsed = false;
        BindingMemberInfo commentMember;
        BindingMemberInfo businessidmember;
        public IList il;
        private Rectangle FavoriteRect;
        private int _selectHeight = 20;
        private bool _favoriteHover = false;
        private bool _favoriteClick;
        private ImageList imageList;
        public bool iskeydown = false;

        #endregion

        #region Constructor

        public CmProjectMenu()
        {
        }
        #endregion

        #region Property

        [DefaultValue(34)]
        [Browsable(false)]
        public override int ItemHeight
        {
            get
            {
                return Font.Height + ItemPadding.Vertical + 4;
            }
            set
            {
                base.ItemHeight = value;
            }
        }

        [DefaultValue(false)]
        [Browsable(false)]
        public bool IsSingleClickUsed
        {
            get { return bIsSingleClickUsed; }
            set { bIsSingleClickUsed = value; }
        }

        [Editor("System.Windows.Forms.Design.DataMemberFieldEditor, System.Design, Version=2.0.0.0, Culture=neutral, PublicKeyToken=b03f5f7f11d50a3a", typeof(System.Drawing.Design.UITypeEditor)), DefaultValue("")]
        public string CommentMember
        {
            get
            {
                return this.commentMember.BindingMember;
            }
            set
            {
                if (value == null)
                {
                    value = "";
                }
                BindingMemberInfo newDisplayMember = new BindingMemberInfo(value);
                if (!newDisplayMember.Equals(this.commentMember))
                {

                    if (((this.DataManager != null) && (value != null)) && ((value.Length != 0) && !this.BindingMemberInfoInDataManager(newDisplayMember)))
                    {
                        //throw new ArgumentException("値が正しくありません");
                    }

                    this.commentMember = newDisplayMember;
                    //                    this.OnValueMemberChanged(EventArgs.Empty);
                    //                    this.OnSelectedValueChanged(EventArgs.Empty);
                }
            }
        }

        [Editor("System.Windows.Forms.Design.DataMemberFieldEditor, System.Design, Version=2.0.0.0, Culture=neutral, PublicKeyToken=b03f5f7f11d50a3a", typeof(System.Drawing.Design.UITypeEditor)), DefaultValue("")]
        public string BusinessIdMember
        {
            get
            {
                return this.businessidmember.BindingMember;
            }
            set
            {
                if (value == null)
                {
                    value = "";
                }
                BindingMemberInfo newDisplayMember = new BindingMemberInfo(value);
                if (!newDisplayMember.Equals(this.businessidmember))
                {

                    if (((this.DataManager != null) && (value != null)) && ((value.Length != 0) && !this.BindingMemberInfoInDataManager(newDisplayMember)))
                    {
                        //throw new ArgumentException("値が正しくありません");
                    }

                    this.businessidmember = newDisplayMember;
                    //                    this.OnValueMemberChanged(EventArgs.Empty);
                    //                    this.OnSelectedValueChanged(EventArgs.Empty);
                }
            }
        }

        [Browsable(true)]
        [Category("Wel")]
        [DefaultValue("")]
        public string Subsyscd
        {
            get { return subsyscd; }
            set
            {
                subsyscd = value;
                CustomersDataSet.Clear();
                Invalidate();
            }
        }

        [Browsable(true)]
        [Category("Wel")]
        [DefaultValue(typeof(Padding), "6,3,3,3")]
        public Padding ItemPadding
        {
            get { return itempadding; }
            set
            {
                itempadding = value;
                Invalidate();
            }
        }

        [Browsable(true)]
        [Category("Wel")]
        [DefaultValue(typeof(Padding), "5,6,5,6")]
        public Padding SlitMargin
        {
            get { return slitmargin; }
            set
            {
                slitmargin = value;
                Invalidate();
            }
        }

        [Browsable(true)]
        [Category("Wel")]
        public Color ItemColor
        {
            get
            {
                if (itemcolor != Color.Empty)
                {
                    return itemcolor;
                }
                else
                {
                    IDesignable lp = Parent as IDesignable;
                    return (lp != null) ? lp.ButtonLabelBackColor : Color.FromArgb(149, 185, 130);
                }
            }
            set
            {
                itemcolor = value;
                Invalidate();
            }
        }

        [Browsable(true)]
        [Category("Wel")]
        public Color CommentColor
        {
            get
            {
                if (commentcolor != Color.Empty)
                {
                    return commentcolor;
                }
                else
                {
                    IDesignable lp = Parent as IDesignable;
                    return (lp != null) ? lp.ButtonLabelFocusBackColor : Color.Orange;
                }
            }
            set
            {
                commentcolor = value;
                Invalidate();
            }
        }

        [Browsable(true)]
        [Category("Wel")]
        [DefaultValue(100)]
        public int ProjectNameWidth
        {
            get { return projectnamewidth; }
            set
            {
                projectnamewidth = value;
                Invalidate();
            }
        }

        [Browsable(false)]
        [DefaultValue(typeof(int), "-1")]
        public int HoverIndex
        {
            get { return hoverindex; }
            set
            {
                if (hoverindex != value)
                {
                    int bh = hoverindex;
                    hoverindex = value;

                    //using (Graphics g = Graphics.FromHwnd(Handle))
                    //{

                    //    if (value >= 0)
                    //    {
                    //        DrawItemCore(g, GetItemRectangle(value), value,(SelectedIndex==value));
                    //    }
                    //    if (bh >= 0)
                    //    {
                    //        DrawItemCore(g, GetItemRectangle(bh), bh, (SelectedIndex == bh));
                    //    }
                    //}

                    Invalidate(GetItemRectangle(value));
                    Invalidate(GetItemRectangle(bh));

                }

            }
        }

        [DefaultValue(20)]
        public int SelectHeight
        {
            get { return _selectHeight; }
            set { _selectHeight = value; }
        }

        [Browsable(false)]
        [DefaultValue(false)]
        public bool FavoriteHover
        {
            get { return _favoriteHover; }
            set
            {
                if (_favoriteHover != value)
                {
                    _favoriteHover = value;
                    Invalidate();
                }
            }
        }

        [Browsable(false)]
        [DefaultValue(false)]
        public bool FavoriteClick
        {
            get { return _favoriteClick; }
            set
            {
                _favoriteClick = value;
                Invalidate();
            }
        }

        [DefaultValue(null)]
        public ImageList ImageList
        {
            get { return imageList; }
            set { imageList = value; }
        }

        private void ResetCommentColor()
        {
            commentcolor = Color.Empty;
            Invalidate();
        }

        private void ResetItemColor()
        {
            itemcolor = Color.Empty;
            Invalidate();
        }

        #endregion

        #region Methods

        public event EventHandler<ProjectMenuEventArgs> ProjectMenuClickAction;

        public event EventHandler<FavoriteEventArgs> FavoriteClickAction;

        private void DrawItemCore(Graphics g, Rectangle r, int index, bool selected)
        {
            //g.FillRectangle(new SolidBrush(BackColor), r);

            if (index < 0) { return; }
            object o = DataSource;
            IListSource ils = o as IListSource;
            il = (ils != null) ? ils.GetList() : (o as IList);
            if (il != null && il.Count > 0)
            {

                string caption = (string.IsNullOrEmpty(DisplayMember)) ? "" : FilterItemOnProperty(il[index], DisplayMember).ToString();
                string comment = (string.IsNullOrEmpty(CommentMember)) ? "" : FilterItemOnProperty(il[index], commentMember.BindingField).ToString();

                g.SmoothingMode = System.Drawing.Drawing2D.SmoothingMode.AntiAlias;

                //全体的な四角（影のため）
                Rectangle ItemRect;
                ItemRect = new Rectangle(r.X + SlitMargin.Left, r.Y + SlitMargin.Top, r.Width - SlitMargin.Horizontal - 3, r.Height - SlitMargin.Vertical);
                GraphicsPath gp = CaptionGraphics.CreateRoundRectPath(ItemRect, 5, 5, true, true, true, true);
                CaptionGraphics.DrawShadow(g, gp);


                //業務名の四角
                Rectangle ItemRect1 = new Rectangle(r.X + SlitMargin.Left, r.Y + SlitMargin.Top, ProjectNameWidth, r.Height - SlitMargin.Vertical);
                //g.FillRectangle(new SolidBrush(ItemColor), ItemRect1);
                GraphicsPath gp1 = CaptionGraphics.CreateRoundRectPath(ItemRect1, 5, 5, true, false, true, false);

                Color bc = (selected) ? CommentColor : ItemColor;

                //bc = (HoverIndex != index) ? bc : CaptionGraphics.BlendColor(bc, Color.Black, 80);


                g.FillPath(new SolidBrush(bc), gp1);


                //業務名表示用
                Rectangle ProjectNameRect = new Rectangle(((imageList != null) ? imageList.Images[index].Width + 4 : 0) + ItemRect1.X + itempadding.Left, ItemRect1.Y + ItemRect1.Height / 2 - Font.Height / 2, ItemRect1.Width - ItemPadding.Horizontal - 30, ItemRect1.Height - ItemPadding.Vertical);


                if (selected)
                {
                    int intY = 0;

                    if (g.MeasureString(caption, Font).Width > (ItemRect1.Width - ItemPadding.Horizontal))
                    {
                        ProjectNameRect = new Rectangle(ProjectNameRect.X, ProjectNameRect.Top - Font.Height / 2, ProjectNameRect.Width - 30, ProjectNameRect.Height);
                        intY = Font.Height / 2;
                    }
                    g.DrawString(caption, Font, new SolidBrush(Color.White), new Rectangle(ProjectNameRect.X, ProjectNameRect.Top - intY, ProjectNameRect.Width, ProjectNameRect.Height));
                }
                else
                {
                    TextRenderer.DrawText(g, caption, Font, ProjectNameRect, Color.White, TextFormatFlags.EndEllipsis);
                }
                //アイコン四角
                //Rectangle ItemRect0 = new Rectangle(r.X + SlitMargin.Left+3, r.Y + SlitMargin.Top, MyImage.Width, MyImage.Height);
                if (imageList != null)
                {
                    Rectangle ItemRect0 = new Rectangle(ItemRect1.X + itempadding.Left, ItemRect1.Y + ItemRect1.Height / 2 - imageList.Images[index].Height / 2, imageList.Images[index].Width, imageList.Images[index].Height);

                    //アイコン描画
                    g.DrawImage(imageList.Images[index], ItemRect0);
                }

                if (selected)
                {
                    //お気に入り登録ボタン四角形計算
                    FavoriteRect = new Rectangle(ItemRect.X + ItemRect.Width - (Font.Height * 5 + 10), ItemRect1.Y + ItemRect1.Height / 2 - Font.Height / 2 - 3 - SelectHeight / 4, Font.Height * 5 + 3, Font.Height + 6 + SelectHeight / 2);
                }


                //コメント用の四角
                Rectangle ItemRect2 = new Rectangle(ItemRect1.X + ItemRect1.Width, ItemRect1.Y, Width - SlitMargin.Right - ItemRect1.X - ItemRect1.Width - 3, ItemRect1.Height);
                GraphicsPath gp2 = CaptionGraphics.CreateRoundRectPath(ItemRect2, 5, 5, false, true, false, true);
                g.FillPath(new SolidBrush(Color.FromArgb(180, CaptionGraphics.BlendColor(ItemColor, Color.White, 10))), gp2);
                //CaptionGraphics.DrawShadow(g, gp2);
                //g.FillRectangle(new SolidBrush(CommentColor), ItemRect2);


                Rectangle CommentRect = new Rectangle(ItemRect2.X + itempadding.Left, ItemRect2.Y + ItemRect2.Height / 2 - Font.Height / 2, ItemRect2.Width - ItemPadding.Horizontal - (selected ? FavoriteRect.Width : 0) - 6, ItemRect1.Height - ItemPadding.Vertical);

                if (selected && (g.MeasureString(comment, Font).Width > (CommentRect.Width)))
                {

                    g.DrawString(comment, Font, new SolidBrush(CaptionGraphics.BlendColor(ItemColor, Color.Black, 70)), new Rectangle(CommentRect.X, CommentRect.Y - FontHeight / 2, CommentRect.Width, FontHeight * 2));
                    if (((g.MeasureString(comment, Font).Width) / CommentRect.Width >= 2))
                    {
                        g.DrawString("...", Font, new SolidBrush(CaptionGraphics.BlendColor(ItemColor, Color.Black, 70)), new Rectangle(CommentRect.X + 2, CommentRect.Y - FontHeight / 2 + FontHeight + 6, CommentRect.Width, FontHeight));
                    }

                }
                else
                {
                    TextRenderer.DrawText(g, comment, Font, CommentRect, CaptionGraphics.BlendColor(ItemColor, Color.Black, 70), TextFormatFlags.EndEllipsis);
                }

                CaptionGraphics.DrawRoundRect(g, new Pen(new SolidBrush(bc), 2), ItemRect, 5, 5);


                if (selected)
                {

                    Color c = FavoriteHover ? CommentColor : CaptionGraphics.BlendColor(ItemColor, Color.White, 90);
                    GraphicsPath gpf = CaptionGraphics.CreateRoundRectPath(FavoriteRect, 10, 10, true, true, true, true);
                    if (FavoriteClick)
                    {
                        c = CaptionGraphics.BlendColor(CommentColor, Color.Black, 80);
                        g.FillPath(new SolidBrush(c), gpf);
                        CaptionGraphics.DrawShadow(g, gpf);

                    }
                    else
                    {
                        CaptionGraphics.DrawShadow(g, gpf);
                        g.FillPath(new SolidBrush(c), gpf);
                    }
                    TextRenderer.DrawText(g, " お気に入り", Font, FavoriteRect, Color.White, Color.Transparent, TextFormatFlags.HorizontalCenter | TextFormatFlags.VerticalCenter | TextFormatFlags.SingleLine);


                    gpf.Dispose();
                    gpf = null;


                }


                gp.Dispose();
                gp = null;
                gp1.Dispose();
                gp1 = null;
                gp2.Dispose();
                gp2 = null;


            }


            o = null;
            ils = null;


        }

        protected virtual void OnProjectMenuClick(ProjectMenuEventArgs e)
        {
            EventHandler<ProjectMenuEventArgs> de = (EventHandler<ProjectMenuEventArgs>)ProjectMenuClickAction;
            if (de != null)
            {
                de(this, e);
            }
        }

        protected virtual void OnFavoriteClick(FavoriteEventArgs e)
        {
            EventHandler<FavoriteEventArgs> de = (EventHandler<FavoriteEventArgs>)FavoriteClickAction;
            if (de != null)
            {
                de(this, e);
            }
        }

        protected override void OnResize(EventArgs e)
        {
            Invalidate();
            base.OnResize(e);
        }

        protected override void OnDrawItem(DrawItemEventArgs e)
        {
            DrawItemCore(e.Graphics, e.Bounds, e.Index, ((e.State & DrawItemState.Selected) != 0));
            base.OnDrawItem(e);

        }

        protected override void OnMeasureItem(MeasureItemEventArgs e)
        {
            //e.ItemHeight = (e.Index + 1) * 20;
            e.ItemHeight = ItemHeight + ItemPadding.Vertical + SlitMargin.Vertical;

            if (SelectedIndex == e.Index)
            {
                e.ItemHeight += SelectHeight;
            }

            base.OnMeasureItem(e);
        }

        protected override void OnMouseMove(MouseEventArgs e)
        {
            if (!iskeydown)
            {
                HoverIndex = IndexFromPoint(e.Location);
                FavoriteHover = FavoriteRect.Contains(e.Location);
                base.OnMouseMove(e);
                if (HoverIndex >= 0)
                {
                    if (!FavoriteHover)
                    {
                        string businessid = (string.IsNullOrEmpty(BusinessIdMember)) ? "" : FilterItemOnProperty(il[HoverIndex], businessidmember.BindingField).ToString();
                        ProjectMenuEventArgs projectmenuEA = new ProjectMenuEventArgs(4, HoverIndex, businessid, (string.IsNullOrEmpty(DisplayMember)) ? "" : FilterItemOnProperty(il[HoverIndex], DisplayMember).ToString(), (string.IsNullOrEmpty(CommentMember)) ? "" : FilterItemOnProperty(il[HoverIndex], commentMember.BindingField).ToString());
                        OnProjectMenuClick(projectmenuEA);
                    }
                }
            }
            iskeydown = false;

        }

        protected override void OnMouseClick(MouseEventArgs e)
        {
            base.OnMouseClick(e);
            if (HoverIndex >= 0)
            {
                string businessid = (string.IsNullOrEmpty(BusinessIdMember)) ? "" : FilterItemOnProperty(il[HoverIndex], businessidmember.BindingField).ToString();

                if (FavoriteHover)
                {
                    FavoriteEventArgs fEA = new FavoriteEventArgs(businessid.Trim(), (string.IsNullOrEmpty(DisplayMember)) ? "" : FilterItemOnProperty(il[HoverIndex], DisplayMember).ToString());
                    OnFavoriteClick(fEA);
                }
                else
                {
                    businessid = (string.IsNullOrEmpty(BusinessIdMember)) ? "" : FilterItemOnProperty(il[HoverIndex], businessidmember.BindingField).ToString();
                    ProjectMenuEventArgs projectmenuEA = new ProjectMenuEventArgs(1, HoverIndex, businessid, (string.IsNullOrEmpty(DisplayMember)) ? "" : FilterItemOnProperty(il[HoverIndex], DisplayMember).ToString(), (string.IsNullOrEmpty(CommentMember)) ? "" : FilterItemOnProperty(il[HoverIndex], commentMember.BindingField).ToString());
                    if (IsSingleClickUsed)
                    {
                        projectmenuEA.SelectType = 2;
                    }
                    OnProjectMenuClick(projectmenuEA);
                }

            }
        }

        protected override void OnMouseDown(MouseEventArgs e)
        {
            base.OnMouseDown(e);
            if (FavoriteHover)
            {
                FavoriteClick = true;
            }
        }

        protected override void OnMouseUp(MouseEventArgs e)
        {
            base.OnMouseUp(e);

            FavoriteClick = false;

        }

        protected override void OnMouseDoubleClick(MouseEventArgs e)
        {
            base.OnMouseDoubleClick(e);
            if (HoverIndex >= 0)
            {
                if (!FavoriteHover)
                {
                    string businessid = (string.IsNullOrEmpty(BusinessIdMember)) ? "" : FilterItemOnProperty(il[HoverIndex], businessidmember.BindingField).ToString();
                    ProjectMenuEventArgs projectmenuEA = new ProjectMenuEventArgs(2, HoverIndex, businessid, (string.IsNullOrEmpty(DisplayMember)) ? "" : FilterItemOnProperty(il[HoverIndex], DisplayMember).ToString(), (string.IsNullOrEmpty(CommentMember)) ? "" : FilterItemOnProperty(il[HoverIndex], commentMember.BindingField).ToString());
                    OnProjectMenuClick(projectmenuEA);
                }
            }
        }

        protected override void OnKeyDown(KeyEventArgs e)
        {
            iskeydown = true;
            switch (e.KeyCode)
            {
                case Keys.Left:
                case Keys.Up:
                    if (SelectedIndex > 0)
                    {
                        SelectedIndex -= 1;
                    }
                    if (SelectedIndex >= 0)
                    {
                        ProjectMenuEventArgs projectmenuEA = new ProjectMenuEventArgs(5, SelectedIndex, "", (string.IsNullOrEmpty(DisplayMember)) ? "" : FilterItemOnProperty(il[SelectedIndex], DisplayMember).ToString(), (string.IsNullOrEmpty(CommentMember)) ? "" : FilterItemOnProperty(il[SelectedIndex], commentMember.BindingField).ToString());
                        OnProjectMenuClick(projectmenuEA);
                    }
                    break;
                case Keys.Right:
                case Keys.Down:
                    if (SelectedIndex < items.Count - 1)
                    {
                        SelectedIndex += 1;
                    }
                    if (SelectedIndex >= 0)
                    {
                        ProjectMenuEventArgs projectmenuEA = new ProjectMenuEventArgs(5, SelectedIndex, "", (string.IsNullOrEmpty(DisplayMember)) ? "" : FilterItemOnProperty(il[SelectedIndex], DisplayMember).ToString(), (string.IsNullOrEmpty(CommentMember)) ? "" : FilterItemOnProperty(il[SelectedIndex], commentMember.BindingField).ToString());
                        OnProjectMenuClick(projectmenuEA);
                    }
                    break;
                case Keys.Enter:
                    if (SelectedIndex >= 0)
                    {
                        string businessid = (string.IsNullOrEmpty(BusinessIdMember)) ? "" : FilterItemOnProperty(il[SelectedIndex], businessidmember.BindingField).ToString();
                        ProjectMenuEventArgs projectmenuEA = new ProjectMenuEventArgs(0, SelectedIndex, businessid, (string.IsNullOrEmpty(DisplayMember)) ? "" : FilterItemOnProperty(il[SelectedIndex], DisplayMember).ToString(), (string.IsNullOrEmpty(CommentMember)) ? "" : FilterItemOnProperty(il[SelectedIndex], commentMember.BindingField).ToString());
                        OnProjectMenuClick(projectmenuEA);
                    }
                    break;

            }

            base.OnKeyDown(e);
        }

        #endregion

    }

    public class ProjectMenuEventArgs : EventArgs
    {
        private int selectindex;

        public int SelectIndex
        {
            get { return selectindex; }
            set { selectindex = value; }
        }
        private string selectbusinessid;

        public string SelectBusinessID
        {
            get { return selectbusinessid; }
            set { selectbusinessid = value; }
        }
        private string selectbusinessname;

        public string SelectBusinessName
        {
            get { return selectbusinessname; }
            set { selectbusinessname = value; }
        }
        private string selectcomment;

        public string SelectComment
        {
            get { return selectcomment; }
            set { selectcomment = value; }
        }
        private int selectType;

        public int SelectType
        {
            get { return selectType; }
            set { selectType = value; }
        }

        public ProjectMenuEventArgs(int selectType, int selectindex, string selectbusinessid, string selectbusinessname, string selectcomment)
        {
            this.selectType = selectType;
            this.selectindex = selectindex;
            this.selectbusinessid = selectbusinessid;
            this.selectbusinessname = selectbusinessname;
            this.selectcomment = selectcomment;
        }
    }

    public class FavoriteEventArgs : EventArgs
    {

        private string selectbusinessid;

        public string SelectBusinessID
        {
            get { return selectbusinessid; }
            set { selectbusinessid = value; }
        }
        private string selectbusinessname;

        public string SelectBusinessName
        {
            get { return selectbusinessname; }
            set { selectbusinessname = value; }
        }

        public FavoriteEventArgs(string selectbusinessid, string selectbusinessname)
        {
            this.selectbusinessid = selectbusinessid;
            this.selectbusinessname = selectbusinessname;
        }
    }
}
