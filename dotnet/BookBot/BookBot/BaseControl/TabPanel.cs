using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.ComponentModel;
using System.Drawing;
using System.Drawing.Drawing2D;
using Common;

namespace Common.BaseControl
{
    [Designer(typeof(TabPanelDesigner)), DefaultProperty("Text")]
    public class TabPanel : CaptionBasePanel, ICaptionPanel
    {

        private ColorTimer _timer;
        private static Padding _def_innermargin = new Padding(8, 8, 8, 8);
        //private Dictionary<Control, Rectangle> _tabrects = new Dictionary<Control, Rectangle>(); 
        private static int CONTROLER_WIDTH = 32;
        private List<Control> _hideControls = new List<Control>();

        public TabPanel()
        {
            DoubleBuffered = true;
            _timer = new ColorTimer();
            _timer.ColorChange += new EventHandler(_timer_ColorChange);
            _timer.ColorChangeStop += new EventHandler(_timer_ColorChangeStop);
            Foldable = false;
            TabStop = true;
            CaptionMargin = new Padding(4, 3, 4, 3);
        }


        protected override void Dispose(bool disposing)
        {
            if (this._timer != null)
            {
                this._timer.ColorChange -= new EventHandler(_timer_ColorChange);
                this._timer.ColorChangeStop -= new EventHandler(_timer_ColorChangeStop);
                this._timer.Dispose();
                this._timer = null;
            }
            if (this._hideControls != null)
            {
                this._hideControls.Clear();
                this._hideControls = null;
            }
            base.Dispose(disposing);
        }


        void _timer_ColorChangeStop(object sender, EventArgs e)
        {
            _clickableColor = Color.Empty;
        }

        void _timer_ColorChange(object sender, EventArgs e)
        {
            ClickableColor = _timer.Color;
        }

        public void AddTab(Form form)
        {
            AddTab(form, form.Text);
        }
        public void AddTab(Form form, string title)
        {
            form.Visible = false;
            form.Resize -= new EventHandler(form_Resize);
            form.TopLevel = false;
            form.WindowState = FormWindowState.Normal;
            form.Dock = DockStyle.Fill;
            form.Margin = Padding.Empty;
            form.FormBorderStyle = FormBorderStyle.None;


            InnerTab it = new InnerTab(form);
            it.Text = title;

            Controls.Add(it);
        }



        public void RemoveTab(Control c)
        {
            Controls.Remove(c);
            c.Visible = false;

            InnerTab it = c as InnerTab;
            if (it != null)
            {
                Form form = it.MainControl as Form;
                it.MainRemove();

                if (form != null)
                {
                    form.Visible = false;
                    form.TopLevel = true;
                    form.Dock = DockStyle.None;
                    form.FormBorderStyle = FormBorderStyle.Sizable;

                    Rectangle r = RectangleToScreen(new Rectangle(0, 0, Width, Height - CaptionPadding.Top));

                    Size bd = SystemInformation.Border3DSize;

                    form.SetBounds(r.X - bd.Width, r.Y + CaptionPadding.Top - SystemInformation.CaptionHeight - bd.Height, r.Width + bd.Width * 2, Height + bd.Height * 2);

                    form.Resize += new EventHandler(form_Resize);

                    Control p = Parent;
                    while (!(p is Form))
                    {
                        if (p == null)
                        {
                            break;
                        }
                        p = p.Parent;
                    }
                    if (p != null)
                    {
                        form.Show((Form)p);
                    }
                    else
                    {
                        form.Show();
                    }
                }
            }
        }

        [DefaultValue(true)]
        public new bool TabStop
        {
            get
            {
                return base.TabStop;
            }
            set
            {
                base.TabStop = value;
            }
        }


        protected bool _isTab = true;

        [DefaultValue(true)]
        public virtual bool IsTab
        {
            get { return _isTab; }
            set
            {
                if (_isTab != value)
                {
                    SuspendLayout();
                    Control selected = SelectedTabControl;
                    _isTab = value;
                    if (Controls.Count > 0)
                    {
                        foreach (Control c in Controls)
                        {
                            TabSetting(c);
                        }

                        //if (_isTab)
                        //{
                        if (selected == null)
                        {
                            selected = SelectableFirstTab();
                        }

                        SelectedTabControl = selected;
                        //}
                    }
                    ResumeLayout(false);
                    PerformLayout();
                    Invalidate();
                }
            }
        }

        protected virtual void TabSetting(Control c)
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
                    InnerTab it = c as InnerTab;
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
                    InnerTab it = c as InnerTab;
                    if (it != null)
                    {
                        it.MainControl.AutoSize = true;
                    }
                }
                c.Dock = DockStyle.Top;
                if (TabVisible(c))
                {
                    c.Visible = true;
                }
                else
                {
                    c.Visible = false;
                }

            }
        }

        void form_Resize(object sender, EventArgs e)
        {
            Form frm = (Form)sender;
            if ((frm).WindowState == FormWindowState.Minimized)
            {
                AddTab(frm);
            }
        }

        /// <summary>
        /// 選択可能な最初のコントロール
        /// </summary>
        /// <returns>コントロール</returns>
        private Control SelectableFirstTab()
        {

            foreach (Control c in Controls)
            {
                if (_hideControls.IndexOf(c) < 0)
                {
                    return c;
                }
            }

            return null;
        }
        /// <summary>
        /// タブの可視状態設定
        /// </summary>
        /// <param name="c">対象コントロール</param>
        /// <param name="visible">可視状態</param>
        public void SetTabVisible(Control c, bool visible)
        {
            if (TabVisible(c))
            {
                if (!visible)
                {
                    _hideControls.Add(c);
                    c.Visible = false;
                    LeftReset();
                    SelectedTabControl = SelectableFirstTab();
                    Invalidate();
                }
            }
            else
            {
                if (visible)
                {
                    _hideControls.Remove(c);
                    LeftReset();
                    Invalidate();

                }
            }

        }


        public bool TabVisible(Control c)
        {
            return !_hideControls.Contains(c);
        }

        protected override void OnControlAdded(ControlEventArgs e)
        {

            base.OnControlAdded(e);

            e.Control.TextChanged += new EventHandler(Control_Changed);
            e.Control.EnabledChanged += new EventHandler(Control_Changed);
            CaptionBasePanel cbp = e.Control as CaptionBasePanel;
            if (cbp != null)
            {
                cbp.IconChanged += new EventHandler(Control_IconChanged);
                cbp.WaitingTick += new EventHandler(Control_IconChanged);
            }

            TabSetting(e.Control);
            // TabRectsCalc();
            SelectedTabControl = e.Control;
            Invalidate();
        }

        protected override void OnControlRemoved(ControlEventArgs e)
        {
            base.OnControlRemoved(e);

            if (_hideControls.Contains(e.Control))
            {
                _hideControls.Remove(e.Control);
            }

            e.Control.TextChanged -= new EventHandler(Control_Changed);
            e.Control.EnabledChanged -= new EventHandler(Control_Changed);
            CaptionBasePanel cbp = e.Control as CaptionBasePanel;
            if (cbp != null)
            {
                cbp.IconChanged -= new EventHandler(Control_IconChanged);
                cbp.WaitingTick -= new EventHandler(Control_IconChanged);
            }

            //if (Controls.Count > 0)
            //{
            //    SelectedTabControl = SelectableFirstTab();
            //}
            // _tabrects.Remove(e.Control);
            //TabRectsCalc();
            Invalidate();
        }

        private void Control_Changed(object sender, EventArgs e)
        {
            //throw new Exception("The method or operation is not implemented.");
            //TabRectsCalc();
            //Invalidate();
            LeftReset();
            Invalidate();
        }

        private void Control_IconChanged(object sender, EventArgs e)
        {
            if (IsTab)
            {
                Invalidate(new Rectangle(0, 0, Width, CaptionPadding.Top));
            }
        }

        //private void TabRectsCalc()
        //{
        //    int h = FontHeight + CaptionMargin.Vertical + 5;


        //    int l = 0;
        //    for (int i = 0; i < Controls.Count; i++)
        //    {
        //        Control c = Controls[i];
        //        int w=TabWidth(c.Text);
        //        _tabrects[c] = new Rectangle(l, 0, w, h);
        //        l += w;
        //    }
        //}


        [Browsable(false)]
        public override bool Foldable
        {
            get
            {
                return false;
            }
            set
            {
                base.Foldable = false;
            }
        }

        [Browsable(false)]
        public override bool FolderOpened
        {
            get
            {
                return true;
            }
            set
            {
                base.FolderOpened = true;
            }
        }

        private Color _clickableColor = Color.Empty;
        private Color ClickableColor
        {
            get
            {
                return (_clickableColor == Color.Empty) ? ButtonLabelBackColor : _clickableColor;
            }
            set
            {
                if (_clickableColor != value)
                {
                    _clickableColor = value;
                    Invalidate(new Rectangle(0, 0, Width, CaptionPadding.Top));
                }
            }
        }

        private bool _captionHover;

        /// <summary>
        /// キャプションの上にカーソルがあるかを設定する内部処理用プロパティ
        /// </summary>
        protected override bool CaptionHover
        {
            get
            {
                return _captionHover;
            }
            set
            {
                if (_captionHover != value)
                {
                    _captionHover = value;
                    if (value)
                    {
                        _timer.Stop(ButtonLabelFocusBackColor);
                    }
                    else
                    {
                        Color c = ButtonLabelBackColor;
                        if (HoverControl != null)
                        {
                            CaptionBasePanel cbp = HoverControl as CaptionBasePanel;
                            if (cbp != null)
                            {
                                c = cbp.ButtonLabelBackColor;
                            }
                        }

                        _timer.Start(c);
                    }
                }
            }
        }

        private Control _hoverControl;

        public Control HoverControl
        {
            get { return _hoverControl; }
            set
            {
                if (_hoverControl != value)
                {
                    _hoverControl = value;
                    Invalidate(new Rectangle(0, 0, Width, CaptionPadding.Top));
                }
            }
        }

        /// <summary>
        /// キャプション描画用余白指定インターフェイス処理用プロパティ
        /// </summary>
        [Browsable(false)]
        public override Padding CaptionPadding
        {
            get
            {
                if (!IsTab)
                {
                    return Padding.Empty;
                }
                //else if (!TextVisible)
                //{
                // return _def_innermargin;
                //}
                else
                {
                    return new Padding(0, LabelSize.Height, 0, 0) + _def_innermargin;
                }

            }
        }

        [Browsable(false)]
        public override bool AddControlOrder
        {
            get
            {
                return true;
            }
        }

        [DefaultValue(typeof(Padding), "4,3,4,3")]
        public override Padding CaptionMargin
        {
            get { return base.CaptionMargin; }
            set
            {
                base.CaptionMargin = value;

            }
        }

        protected override void OnPaintBackground(PaintEventArgs e)
        {
            //必要な部分の再描画

            if (Parent != null)
            {
                e.Graphics.FillRectangle(new SolidBrush((Parent.BackColor != Color.Transparent) ? Parent.BackColor : BackColor), e.ClipRectangle);
            }
            else
            {
                base.OnPaintBackground(e);
            }

        }

        private int TabWidth(Control c)
        {
            if (!TabVisible(c))
            {
                return 0;
            }


            int mk = FontHeight;
            if (mk < 16) { mk = 16; }

            //強調対応
            Font f = Font;
            if (c is CaptionBasePanel)
            {
                if (((CaptionBasePanel)c).Enphasis)
                {
                    f = new Font(Font, FontStyle.Bold);
                }

            }


            return (int)Math.Max(Font.Height * LabelCharCount, TextRenderer.MeasureText(c.Text, f).Width + FontHeight + mk + CaptionMargin.Horizontal);
        }

        private int _leftTab = 0;
        private int LeftTab
        {
            get
            {
                return _leftTab;
            }
            set
            {
                if (_leftTab != value)
                {
                    _leftTab = value;

                    LeftReset();

                    PerformLayout();
                    Invalidate();
                }
            }
        }

        private int RightTab
        {
            get
            {
                if (Controls.Count > 0)
                {
                    //横幅が足りない場合、コントロール用のスペースを確保する
                    if (!TabRightOver())
                    {
                        return Controls.Count - 1;
                    }

                    int cwidth = Width - CONTROLER_WIDTH - 6;

                    int rtn = LeftTab;

                    for (int i = LeftTab + 1; i < Controls.Count; i++)
                    {
                        Rectangle ltab = TabRect(LeftTab, Controls[i]);
                        if (ltab.Right <= cwidth)
                        {
                            rtn = i;
                        }
                        else
                        {
                            break;
                        }
                    }

                    return rtn;
                }
                return 0;

            }
            set
            {
                if (Controls.Count > 0)
                {

                    if (!TabRightOver())
                    {
                        LeftTab = 0;
                        return;
                    }
                    int right = Width - CONTROLER_WIDTH - 6;

                    int rtn = value;

                    for (int i = value; i >= 0; i--)
                    {
                        int tabw = TabWidth(Controls[i]);
                        right -= tabw;
                        if (right >= 0)
                        {
                            rtn = i;
                        }
                        else
                        {
                            break;
                        }
                    }

                    LeftTab = rtn;


                }

            }
        }


        private void LeftReset()
        {
            if (IsTab)
            {
                if (!TabRightOver())
                {
                    LeftTab = 0;
                }
                else
                {
                    int left = Width - CONTROLER_WIDTH - 6;
                    int rtn = Controls.Count - 1;
                    for (int i = Controls.Count - 1; i >= 0; i--)
                    {
                        Control cc = Controls[i];
                        int w = TabWidth(cc);

                        left -= w;

                        if (left < (6 + CONTROLER_WIDTH))
                        {
                            break;
                        }
                        rtn = i;

                    }
                    if (LeftTab > rtn)
                    {
                        LeftTab = rtn;
                    }



                }
            }

        }



        private void DispTab(int idx)
        {
            if (idx < LeftTab)
            {
                LeftTab = idx;
            }
            else if (idx > RightTab)
            {
                RightTab = idx;
            }
        }

        private bool TabRightOver()
        {
            if (Controls.Count > 0)
            {
                if (TabRect(0, Controls[Controls.Count - 1]).Right > Width - 6)
                {
                    return true;
                }
            }
            return false;
        }

        protected bool IsMouseOnArrow { get; set; }

        private Rectangle TabRect(int left, Control c)
        {

            int h = FontHeight + CaptionMargin.Vertical + 5;

            Rectangle[] rects = new Rectangle[Controls.Count];
            Rectangle r = Rectangle.Empty;

            int l = 0;
            for (int i = 0; i < Controls.Count; i++)
            {
                Control cc = Controls[i];
                int w = TabWidth(cc);
                rects[i] = new Rectangle(l, 0, w, h);
                if (c == cc)
                {
                    r = rects[i];
                }

                l += w;
            }


            //LeftTab分移動
            int leftshift = 6;

            if (left > 0)
            {

                Rectangle lr = rects[left];
                leftshift -= lr.Left;

                //前タブの見える幅
                int pw = CONTROLER_WIDTH;
                leftshift += pw;

                //幅から制御幅とLeftタブ幅を引いた余白を取得

                //Rectangle lastr= rects[Controls.Count - 1];

                //int spc = Width - CONTROLER_WIDTH - (lastr.Right + leftshift);

                //if (spc < -leftshift)
                //{
                //    leftshift += spc;

                //}

            }
            r.Offset(leftshift, 0);
            return r;
        }

        public int SelectedTabIndex
        {
            get
            {
                if (IsTab)
                {

                    for (int i = 0; i < Controls.Count; i++)
                    {
                        if (Controls[i].Visible)
                        {
                            return i;
                        }
                    }
                }
                else
                {
                    for (int i = 0; i < Controls.Count; i++)
                    {
                        CaptionBasePanel cbp = Controls[i] as CaptionBasePanel;
                        if (cbp != null)
                        {
                            if (cbp.FolderOpened && cbp.ContainsFocus)
                            {
                                return i;
                            }
                        }
                    }
                }
                return -1;
            }
            set
            {
                SelectedTabControl = (value >= 0 && value < Controls.Count) ? Controls[value] : null;
            }
        }


        public Control SelectedTabControl
        {
            get
            {
                int idx = SelectedTabIndex;
                return (idx >= 0 && idx < Controls.Count) ? Controls[idx] : null;
            }
            set
            {
                if (IsTab)
                {
                    if (SelectedTabControl != value)
                    {
                        SuspendLayout();
                        for (int i = 0; i < Controls.Count; i++)
                        {
                            Control c = Controls[i];

                            if (!c.IsHandleCreated)
                            {
                                IntPtr ip = c.Handle;
                            }

                            if (c == value)
                            {
                                if (TabVisible(c))
                                {
                                    c.Visible = true;
                                }
                                else
                                {
                                    c.Visible = false;
                                }

                            }
                            else
                            {
                                c.Visible = false;
                            }
                        }

                        LeftReset();
                        DispTab(SelectedTabIndex);
                        ResumeLayout(false);
                        PerformLayout();
                        Invalidate();
                    }
                    if (null != SelectedTabControl && SelectedTabControl.Controls.Count > 0
                        && null != SelectedTabControl.Controls[0] && SelectedTabControl.Controls[0] is Form)
                    {
                        SelectedTabControl.Controls[0].Focus();
                    }
                }
                else
                {
                    CaptionBasePanel cbp = value as CaptionBasePanel;
                    if (cbp != null)
                    {
                        cbp.FolderOpened = true;
                        cbp.Focus();
                    }
                }
            }
        }

        public void SelectNextTab()
        {
            int idx = SelectedTabIndex;
            for (int i = idx + 1; i < Controls.Count; i++)
            {
                if (TabVisible(Controls[i]))
                {
                    SelectedTabControl = Controls[i];
                    break;
                }
            }
        }

        public void SelectPrevTab()
        {
            int idx = SelectedTabIndex;
            for (int i = idx - 1; i >= 0; i--)
            {
                if (TabVisible(Controls[i]))
                {
                    SelectedTabControl = Controls[i];
                    break;
                }
            }
        }

        /// <summary>
        /// 描画処理の拡張
        /// </summary>
        /// <param name="e"></param>
        protected override void OnPaint(PaintEventArgs e)
        {
            int clheight = DisplayRectangle.Height;
            int clwidth = DisplayRectangle.Width;
            e.Graphics.TranslateTransform(DisplayRectangle.X, DisplayRectangle.Y);


            if (IsTab)
            {


                Brush foreBrush = new SolidBrush(ButtonLabelForeColor);
                Color fc = ButtonLabelForeColor;
                e.Graphics.SmoothingMode = SmoothingMode.AntiAlias;
                int labelHeight = FontHeight + CaptionMargin.Vertical + 6;

                Control selected = null;


                Rectangle cr = e.ClipRectangle;

                e.Graphics.SetClip(new Rectangle(5, 0, clwidth - 10, clheight));

                for (int i = Controls.Count - 1; i >= 0; i--)
                {
                    Control c = Controls[i];
                    if (TabVisible(c))
                    {
                        //タブ描画
                        if (!c.Visible)
                        {

                            if (i >= LeftTab - 1)
                            {
                                Rectangle r = TabRect(LeftTab, c);

                                if (r.Left < clwidth)
                                {

                                    Rectangle rt = r;
                                    if (i > 0)
                                    {
                                        rt.X -= 6;
                                        rt.Width += 6;
                                    }
                                    rt.Y += 2;
                                    rt.Height -= 2;

                                    CaptionBasePanel cbp = c as CaptionBasePanel;

                                    Color bc = CaptionGraphics.BlendColor((cbp != null) ? cbp.ButtonLabelBackColor : ButtonLabelBackColor, Color.White, 60);


                                    GraphicsPath gp = CaptionGraphics.CreateRoundRectPath(rt, 4, 4, true, true, false, false);
                                    CaptionGraphics.DrawShadow(e.Graphics, gp, 1, 3, 20);

                                    e.Graphics.FillPath((HoverControl == c) ? new SolidBrush(CaptionGraphics.BlendColor(ClickableColor, bc, 20)) : new SolidBrush(bc), gp);

                                    if (cbp != null)
                                    {
                                        cbp.DrawMarkAndText(e.Graphics, Font, ButtonLabelForeColor, ButtonLabelForeColor, new Point(r.Left + CaptionMargin.Left, rt.Y + CaptionMargin.Top), true);

                                    }
                                    else
                                    {
                                        e.Graphics.FillEllipse(foreBrush, r.Left + CaptionMargin.Left, rt.Y + CaptionMargin.Top, FontHeight, FontHeight);
                                        TextRenderer.DrawText(e.Graphics, c.Text, Font, new Point(r.Left + (int)(1.5f * FontHeight), rt.Y + CaptionMargin.Top), ButtonLabelForeColor);
                                    }
                                }
                            }
                        }
                        else
                        {
                            selected = c;
                        }
                    }

                }

                e.Graphics.SetClip(cr);

                if (selected != null)
                {
                    CaptionBasePanel cbp = selected as CaptionBasePanel;

                    Rectangle sr = TabRect(LeftTab, selected);

                    GraphicsPath gp = CaptionGraphics.CreateRoundRectPath(sr, 4, 4, true, true, false, false);

                    CaptionGraphics.DrawShadow(e.Graphics, gp);

                    //複雑な形状なので全体再描画
                    Rectangle r = new Rectangle(6, labelHeight, clwidth - 12, clheight - labelHeight - 6);
                    GraphicsPath gpf = CaptionGraphics.CreateRoundRectPath(r, 2, 2, false, true, true, true);

                    CaptionGraphics.DrawShadow(e.Graphics, gpf);

                    Color blb = ButtonLabelBackColor;
                    Color bbc = BackColor;
                    if (cbp != null)
                    {
                        blb = cbp.ButtonLabelBackColor;
                        bbc = cbp.BackColor;

                    }

                    Brush brush = new SolidBrush((HoverControl == selected) ? ClickableColor : blb);
                    e.Graphics.FillPath(new SolidBrush(bbc), gpf);

                    Pen p = new Pen(blb, 2);
                    e.Graphics.DrawPath(p, gpf);
                    e.Graphics.FillPath(brush, gp);
                    e.Graphics.DrawPath(new Pen(brush, 2), gp);

                    if (Focused)
                    {
                        e.Graphics.DrawPath(new Pen(ButtonLabelFocusBackColor, 1), gp);
                    }

                    Icon ico = null;
                    if (selected is InnerTab)
                    {
                        InnerTab it = selected as InnerTab;
                        Form fm = it.MainControl as Form;
                        if (fm != null)
                        {
                            ico = fm.Icon;
                        }
                    }

                    if (cbp != null)
                    {
                        cbp.DrawMarkAndText(e.Graphics, Font, Focused ? ButtonLabelFocusBackColor : fc, fc, new Point(sr.Left + CaptionMargin.Left, sr.Top + CaptionMargin.Top));
                    }
                    else
                    {
                        e.Graphics.FillEllipse(new SolidBrush(Focused ? ButtonLabelFocusBackColor : fc), sr.Left + CaptionMargin.Left, CaptionMargin.Top + 1, FontHeight, FontHeight);
                        TextRenderer.DrawText(e.Graphics, selected.Text, Font, new Point(CaptionMargin.Left + sr.Left + (int)(1.5f * FontHeight), CaptionMargin.Top + 1), fc);
                    }
                }
                else
                {
                    //複雑な形状なので全体再描画
                    Rectangle r = new Rectangle(6, labelHeight, clwidth - 12, clheight - labelHeight - 6);
                    GraphicsPath gpf = CaptionGraphics.CreateRoundRectPath(r, 2, 2, false, true, true, true);

                    CaptionGraphics.DrawShadow(e.Graphics, gpf);

                    e.Graphics.FillPath(Brushes.Gainsboro, gpf);
                    e.Graphics.DrawPath(new Pen(Color.Silver, 2), gpf);
                }

                if (TabRightOver())
                {
                    //コントローラー描画
                    Brush brush = new SolidBrush(ButtonLabelBackColor);

                    int ar = CONTROLER_WIDTH / 8;

                    //CONTROLER_WIDTH

                    Rectangle r = new Rectangle(clwidth - CONTROLER_WIDTH - 6, 0, CONTROLER_WIDTH / 2, CONTROLER_WIDTH / 2);

                    GraphicsPath gp = CaptionGraphics.CreateRoundRectPath(r, 4, 4);
                    CaptionGraphics.DrawShadow(e.Graphics, gp);
                    e.Graphics.FillPath((LeftTab > 0) ? brush : Brushes.Silver, gp);
                    e.Graphics.FillPolygon(foreBrush, new Point[] { new Point(r.X + ar, r.Y + r.Height / 2), new Point(r.X + ar * 3, r.Y + r.Height / 2 + ar), new Point(r.X + ar * 3, r.Y + r.Height / 2 - ar) });

                    r = new Rectangle(clwidth - CONTROLER_WIDTH / 2 - 5, 0, CONTROLER_WIDTH / 2, CONTROLER_WIDTH / 2);
                    gp = CaptionGraphics.CreateRoundRectPath(r, 4, 4);
                    CaptionGraphics.DrawShadow(e.Graphics, gp);
                    e.Graphics.FillPath((RightTab < Controls.Count - 1) ? brush : Brushes.Silver, gp);
                    e.Graphics.FillPolygon(foreBrush, new Point[] { new Point(r.X + ar * 3, r.Y + r.Height / 2), new Point(r.X + ar, r.Y + r.Height / 2 + ar), new Point(r.X + ar, r.Y + r.Height / 2 - ar) });
                }
            }
            e.Graphics.TranslateTransform(-DisplayRectangle.X, -DisplayRectangle.Y);

            base.OnPaint(e);
        }

        protected override void OnMouseMove(MouseEventArgs e)
        {

            base.OnMouseMove(e);
            Control c = null;
            IsMouseOnArrow = false;

            if (e.Y < CaptionPadding.Top && IsTab)
            {
                c = TabControlFromPos(e.Location);

                if (TabRightOver())
                {
                    //コントローラー描画
                    //CONTROLER_WIDTH
                    Rectangle r = new Rectangle(Width - CONTROLER_WIDTH - 6, 0, CONTROLER_WIDTH + 6, CONTROLER_WIDTH);
                    if (r.Contains(e.Location))
                    {
                        c = null;
                        IsMouseOnArrow = true;
                    }
                }
            }
            if (c != null && c.Enabled)
            {
                HoverControl = c;
                CaptionHover = true;
            }
            else
            {
                CaptionHover = false;
            }
        }



        public Control TabControlFromPos(Point p)
        {
            for (int i = 0; i < Controls.Count; i++)
            {
                Control c = Controls[i];
                Rectangle r = TabRect(LeftTab, c);
                if (TabVisible(c) && r.Contains(p))
                {
                    return c;
                }
            }
            return null;
        }

        protected override void OnMouseClick(MouseEventArgs e)
        {
            base.OnMouseClick(e);
            if (e.Button == MouseButtons.Left)
            {
                if (TabRightOver())
                {
                    //コントローラー描画
                    //CONTROLER_WIDTH
                    Rectangle r = new Rectangle(Width - CONTROLER_WIDTH - 6, 0, CONTROLER_WIDTH / 2, CONTROLER_WIDTH / 2);
                    if (r.Contains(e.Location))
                    {
                        if (LeftTab > 0)
                        {
                            LeftTab -= 1;

                        }
                        return;
                    }

                    r = new Rectangle(Width - CONTROLER_WIDTH / 2 - 5, 0, CONTROLER_WIDTH / 2, CONTROLER_WIDTH / 2);
                    if (r.Contains(e.Location))
                    {
                        if (LeftTab < Controls.Count - 1)
                        {
                            LeftTab += 1;

                        }
                        return;
                    }
                }
            }
        }

        protected override void OnMouseDoubleClick(MouseEventArgs e)
        {
            base.OnMouseDoubleClick(e);
            if (e.Button == MouseButtons.Left)
            {
                if (TabRightOver())
                {
                    //コントローラー描画
                    //CONTROLER_WIDTH
                    Rectangle r = new Rectangle(Width - CONTROLER_WIDTH - 6, 0, CONTROLER_WIDTH, CONTROLER_WIDTH / 2);
                    if (r.Contains(e.Location))
                    {
                        return;
                    }
                }
            }
        }



        protected override void OnMouseDown(MouseEventArgs e)
        {

            //CONTROLER_WIDTH
            Rectangle r = new Rectangle(Width - CONTROLER_WIDTH - 6, 0, CONTROLER_WIDTH, CONTROLER_WIDTH / 2);

            if (e.Button == MouseButtons.Right)
            {
                if (TabRightOver())
                {
                    if (r.Contains(e.Location))
                    {
                        //メニュー表示

                        ContextMenuStrip cms = new ContextMenuStrip();
                        foreach (Control c in Controls)
                        {
                            if (TabVisible(c))
                            {
                                Image img = null;
                                CaptionBasePanel wc = c as CaptionBasePanel;
                                if (wc != null)
                                {
                                    if (wc.Icon != null)
                                    {
                                        img = wc.Icon.ToBitmap();
                                    }
                                }

                                ToolStripItem item = cms.Items.Add(c.Text, img);
                                item.Tag = c;
                            }
                        }
                        cms.ItemClicked += new ToolStripItemClickedEventHandler(Menu_ItemClicked);
                        cms.Show(this, e.Location);
                        return;
                    }
                }
            }
            else
            {
                Control c = TabControlFromPos(e.Location);
                if (c != null && !IsMouseOnArrow)
                {
                    SelectedTabControl = c;
                }
            }
            base.OnMouseDown(e);
        }

        protected override bool ProcessDialogKey(Keys keyData)
        {
            //プリプロセス処理
            //独自処理を加えた場合、戻り値をTrueにする必要がある
            switch (keyData)
            {
                case Keys.Down:
                case Keys.Right:
                    {

                        SelectNextTab();
                    }
                    break;
                case Keys.Up:
                case Keys.Left:
                    {
                        SelectPrevTab();
                    }
                    break;
                default:
                    return base.ProcessDialogKey(keyData);
            }
            return true;
        }


        void Menu_ItemClicked(object sender, ToolStripItemClickedEventArgs e)
        {
            Control c = (Control)e.ClickedItem.Tag;
            SelectedTabControl = c;
        }

        protected override void OnResize(EventArgs eventargs)
        {
            base.OnResize(eventargs);

            LeftReset();
            DispTab(SelectedTabIndex);


            PerformLayout();
            Invalidate();
        }


        class InnerTab : BlockCaptionPanel
        {
            public InnerTab(Control c)
            {
                TextVisible = false;
                Dock = DockStyle.Fill;
                _originalAutoSize = c.AutoSize;
                Controls.Add(c);

                Form fm = c as Form;
                if (fm != null)
                {
                    this.Icon = fm.Icon;
                    this.BackColor = fm.BackColor;
                }


            }


            private bool _originalAutoSize;

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

            public void MainRemove()
            {
                MainControl.AutoSize = OriginalAutoSize;
                Controls.Remove(MainControl);

            }
        }


    }
}
