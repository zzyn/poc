using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.ComponentModel;
using System.Drawing;
using System.Collections;

namespace Common.CustomControl
{
    public class CmMenuList : ListControl
    {
        #region Fields
        Dictionary<int, int> heights = new Dictionary<int, int>();

        VScrollBar m_VScrollBar;

        readonly static object MeasureItemEvent = new object();

        readonly static object DrawItemEvent = new object();

        protected IList items = null;

        int itemHeight = 30;

        int topIndex = 0;

        int selectedIndex = -1;

        #endregion

        public CmMenuList()
        {
            DoubleBuffered = true;
            SetStyle(ControlStyles.Selectable, true);
            m_VScrollBar = new VScrollBar();
            Controls.Add(m_VScrollBar);
            m_VScrollBar.Dock = DockStyle.Right;
            m_VScrollBar.Minimum = 0;

            m_VScrollBar.Visible = false;
            m_VScrollBar.Scroll += new ScrollEventHandler(VScrollBar_Scroll);
        }

        #region Properties

        [Browsable(true)]
        [DefaultValue(null)]
        public override Image BackgroundImage
        {
            get
            {
                return base.BackgroundImage;
            }
            set
            {
                base.BackgroundImage = value;
            }
        }

        [Browsable(true)]
        [DefaultValue(typeof(System.Windows.Forms.ImageLayout), "Tile")]
        public override ImageLayout BackgroundImageLayout
        {
            get
            {
                return base.BackgroundImageLayout;
            }
            set
            {
                base.BackgroundImageLayout = value;
            }
        }

        [DefaultValue(30)]
        public virtual int ItemHeight
        {
            get { return itemHeight; }
            set
            {
                itemHeight = value;
            }
        }

        [DesignerSerializationVisibility(DesignerSerializationVisibility.Hidden), Browsable(false)]
        public virtual int TopIndex
        {
            get { return topIndex; }
            set
            {
                if (topIndex != value)
                {
                    SetTopIndex(value);
                }
            }
        }

        protected int BottomIndex
        {
            get
            {
                if (items == null)
                {
                    return -1;
                }

                int idx = TopIndex;
                int h = 0;
                for (int i = TopIndex; i < items.Count; i++)
                {
                    h += GetHeight(i);

                    if (h > ClientSize.Height)
                    {
                        break;
                    }
                    idx = i;
                }
                return idx;
            }
        }

        #endregion

        #region Event

        private void VScrollBar_Scroll(object sender, ScrollEventArgs e)
        {
            if (m_VScrollBar.Visible)
            {
                SetTopIndex(m_VScrollBar.Value);
            }
        }

        public event MeasureItemEventHandler MeasureItem
        {
            add
            {
                Events.AddHandler(MeasureItemEvent, value);
            }
            remove
            {
                Events.RemoveHandler(MeasureItemEvent, value);
            }
        }

        public event DrawItemEventHandler DrawItem
        {
            add
            {
                Events.AddHandler(DrawItemEvent, value);
            }
            remove
            {
                Events.RemoveHandler(DrawItemEvent, value);
            }
        }

        protected virtual void OnMeasureItem(MeasureItemEventArgs e)
        {
            MeasureItemEventHandler dlg = (MeasureItemEventHandler)Events[MeasureItemEvent];
            if (dlg != null)
            {
                dlg(this, e);
            }
        }

        protected virtual void OnDrawItem(DrawItemEventArgs e)
        {
            DrawItemEventHandler dlg = (DrawItemEventHandler)Events[DrawItemEvent];
            if (dlg != null)
            {
                dlg(this, e);
            }
            else
            {
                //e.Graphics.DrawString(GetItemText(items[e.Index]),e.Font,new SolidBrush(ForeColor),e.Bounds.Location);
            }
        }

        #endregion

        #region Method

        #region Implement Abstract Class

        protected override void RefreshItem(int index)
        {
            //throw new Exception("The method or operation is not implemented.");
            Invalidate();
        }

        [DesignerSerializationVisibility(DesignerSerializationVisibility.Hidden), Browsable(false), Bindable(true)]
        public override int SelectedIndex
        {
            get
            {
                return selectedIndex;
            }
            set
            {
                if (this.SelectedIndex != value)
                {
                    int oldsel = this.SelectedIndex;
                    selectedIndex = value;

                    DataManager.Position = value;

                    ResetHeight(oldsel);
                    ResetHeight(value);

                    this.OnSelectedIndexChanged(EventArgs.Empty);

                    int ti = TopIndex;

                    if (value < ti)
                    {
                        ti = (value >= 0) ? value : 0;

                    }
                    else if (value > BottomIndex)
                    {
                        ti = GetTopIndexFromBottom(value);
                    }

                    SetTopIndex(ti);
                }
            }
        }

        protected override void SetItemsCore(IList items)
        {
            heights.Clear();
            //using(Graphics g=Graphics.FromHwnd(Handle)){

            //    for (int i=0;i<items.Count;i++){
            //        MeasureItemEventArgs e = new MeasureItemEventArgs(g, i);
            //        e.ItemHeight = ItemHeight;
            //        e.ItemWidth = ClientSize.Width - ((scr.Visible) ? scr.Width : 0);
            //        OnMeasureItem(e);
            //        heights[i] = e.ItemHeight;
            //    }
            //}

            this.items = items;

            SetTopIndex(0);
        }

        #endregion

        #region Override

        protected override void OnKeyPress(KeyPressEventArgs e)
        {
            base.OnKeyPress(e);

        }

        protected override bool IsInputKey(Keys keyData)
        {

            switch ((keyData & Keys.KeyCode))
            {
                case Keys.Left:
                case Keys.Up:
                case Keys.Right:
                case Keys.Down:
                    return true;
            }


            return base.IsInputKey(keyData);
        }

        protected override void OnResize(EventArgs e)
        {
            heights.Clear();
            SetTopIndex(TopIndex);
            base.OnResize(e);
        }

        protected override void OnDataSourceChanged(EventArgs e)
        {
            base.OnDataSourceChanged(e);
        }

        protected override void OnMouseWheel(MouseEventArgs e)
        {

            SetTopIndex(TopIndex + ((e.Delta > 0) ? -1 : ((e.Delta < 0) ? 1 : 0)) * 3);

            base.OnMouseWheel(e);
        }

        protected override void OnMouseDown(MouseEventArgs e)
        {
            this.Focus();
            int idx = IndexFromPoint(e.Location);
            if (idx >= 0)
            {
                SelectedIndex = idx;
            }

            base.OnMouseDown(e);
        }

        protected override void OnMouseMove(MouseEventArgs e)
        {
            //if (e.Button == MouseButtons.Left)
            //{
            int idx = IndexFromPoint(e.Location);
            if (idx >= 0)
            {
                SelectedIndex = idx;
            }
            //}
            base.OnMouseMove(e);
        }

        protected override void OnPaintBackground(PaintEventArgs pevent)
        {
            base.OnPaintBackground(pevent);
            pevent.Graphics.FillRectangle(new SolidBrush(Color.FromArgb(180, BackColor)), pevent.ClipRectangle);
        }

        protected override void OnPaint(PaintEventArgs e)
        {
            Rectangle r = new Rectangle(Point.Empty, new Size(ClientSize.Width - ((m_VScrollBar.Visible) ? m_VScrollBar.Width : 0), 0));

            if (items != null)
            {
                for (int i = TopIndex; i < items.Count; i++)
                {
                    r.Height = GetHeight(i);

                    if (r.Bottom >= e.ClipRectangle.Y)
                    {
                        DrawItemState dis = DrawItemState.Default;
                        if (SelectedIndex == i)
                        {
                            dis |= DrawItemState.Selected;
                        }
                        DrawItemEventArgs de = new DrawItemEventArgs(e.Graphics, Font, r, i, dis);
                        OnDrawItem(de);
                    }

                    r.Y += r.Height;
                    if (r.Y > e.ClipRectangle.Bottom)
                    {
                        break;
                    }
                }
            }

            base.OnPaint(e);

            if (DesignMode)
            {
                Pen p = new Pen(Color.Black);
                e.Graphics.DrawRectangle(p, new Rectangle(0, 0, Width - 1, Height - 1));
            }

        }

        #endregion

        #region Public Method

        public Rectangle GetItemRectangle(int index)
        {
            if (index >= TopIndex)
            {
                Rectangle r = new Rectangle(Point.Empty, new Size(ClientSize.Width - ((m_VScrollBar.Visible) ? m_VScrollBar.Width : 0), 0));
                for (int i = TopIndex; i < items.Count; i++)
                {
                    r.Height = GetHeight(i);

                    if (i == index)
                    {
                        return r;
                    }

                    r.Y += r.Height;
                    if (r.Y > ClientSize.Height)
                    {
                        break;
                    }
                }
            }

            return Rectangle.Empty;
        }

        public int IndexFromPoint(Point p)
        {
            Rectangle r = new Rectangle(Point.Empty, new Size(ClientSize.Width - ((m_VScrollBar.Visible) ? m_VScrollBar.Width : 0), 0));
            if (items != null)
            {


                for (int i = TopIndex; i < items.Count; i++)
                {
                    r.Height = GetHeight(i);

                    if (r.Contains(p))
                    {
                        return i;
                    }

                    r.Y += r.Height;
                    if (r.Y > ClientSize.Height)
                    {
                        break;
                    }
                }
            }
            return -1;

        }

        #endregion

        #region Private Method

        private void ResetHeight(int index)
        {
            heights.Remove(index);
        }

        private int GetHeight(int index)
        {
            if (heights.ContainsKey(index))
            {
                return heights[index];
            }
            else
            {
                using (Graphics g = Graphics.FromHwnd(Handle))
                {
                    MeasureItemEventArgs e = new MeasureItemEventArgs(g, index);
                    e.ItemHeight = ItemHeight;
                    e.ItemWidth = ClientSize.Width - ((m_VScrollBar.Visible) ? m_VScrollBar.Width : 0);
                    OnMeasureItem(e);
                    heights[index] = e.ItemHeight;
                    return e.ItemHeight;
                }
            }
        }

        private void SetTopIndex(int value)
        {
            int v = 0;
            int lb = 0;
            if (items != null)
            {
                lb = GetTopIndexFromBottom(items.Count - 1);
                v = (value > lb) ? lb : ((value < 0) ? 0 : value);

                topIndex = v;
                if (lb > 0)
                {
                    m_VScrollBar.Maximum = items.Count - 1;

                    m_VScrollBar.LargeChange = (items.Count - lb);
                    m_VScrollBar.Value = topIndex;
                    m_VScrollBar.Visible = true;
                }
                else
                {
                    m_VScrollBar.Visible = false;
                }

            }
            else
            {
                topIndex = 0;
                m_VScrollBar.Visible = false;
            }
            Invalidate();

        }

        #endregion

        #region Protected Method

        protected int GetTopIndexFromBottom(int bottomindex)
        {
            if (items == null || items.Count == 0)
            {
                return 0;
            }

            int idx = items.Count - 1;
            int h = ClientSize.Height;
            for (int i = bottomindex; i >= 0; i--)
            {
                h -= GetHeight(i);
                if (h < 0)
                {
                    break;
                }
                idx = i;
            }
            return idx;
        }

        protected bool BindingMemberInfoInDataManager(BindingMemberInfo bindingMemberInfo)
        {
            if (this.DataManager != null)
            {
                PropertyDescriptorCollection itemProperties = this.DataManager.GetItemProperties();
                int count = itemProperties.Count;
                for (int i = 0; i < count; i++)
                {
                    if (!typeof(IList).IsAssignableFrom(itemProperties[i].PropertyType) && itemProperties[i].Name.Equals(bindingMemberInfo.BindingField))
                    {
                        return true;
                    }
                }
                for (int j = 0; j < count; j++)
                {
                    if (!typeof(IList).IsAssignableFrom(itemProperties[j].PropertyType) && (string.Compare(itemProperties[j].Name, bindingMemberInfo.BindingField, true, System.Globalization.CultureInfo.CurrentCulture) == 0))
                    {
                        return true;
                    }
                }
            }
            return false;
        }

        #endregion

        #endregion








    }
}
