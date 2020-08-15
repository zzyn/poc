using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Drawing.Imaging;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Windows.Forms.Layout;
using Common;

namespace Common.BaseControl
{
    [DefaultProperty("Text")]
    public class CaptionBasePanel : DesignablePanel, ICaptionPanel
    {
        
        #region Properties

        Timer _waittimer;

        public bool Waiting
        {
            get { return (_waittimer != null); }
            set
            {
                if (Waiting != value)
                {
                    if (value)
                    {
                        _waittimer = new Timer();
                        _waittimer.Interval = 200;
                        _waittimer.Tick += new EventHandler(_waittimer_Tick);
                        _waittimer.Start();
                    }
                    else
                    {

                        _waittimer.Tick -= new EventHandler(_waittimer_Tick);
                        _waittimer.Dispose();
                        _waittimer = null;
                    }
                    WaitCounter = 0;
                    _waittimer_Tick(null, EventArgs.Empty);
                }
            }
        }

        private void _waittimer_Tick(object sender, EventArgs e)
        {
            WaitCounter++;
            if (WaitCounter == int.MaxValue)
            {
                WaitCounter = 0;
            }
            if (TextVisible)
            {
                Invalidate(new Rectangle(0, 0, Width, CaptionPadding.Top));
            }
            OnWaitingTick(EventArgs.Empty);
        }

        int _waitcounter;

        public int WaitCounter
        {
            get { return _waitcounter; }
            set { _waitcounter = value; }
        }

        Icon _icon;

        [AmbientValue((string)null), Localizable(true), DefaultValue(null)]
        public virtual Icon Icon
        {
            get { return _icon; }
            set
            {
                if (_icon != value)
                {
                    _icon = value;

                    _smallicon = (_icon == null) ? null : new Icon(_icon, new Size(16, 16));

                    if (TextVisible)
                    {
                        Invalidate(new Rectangle(0, 0, Width, CaptionPadding.Top));
                    }

                    _labelSize = CALC_SIZE;

                    OnIconChanged(EventArgs.Empty);
                }
            }
        }

        Icon _smallicon;

        [Browsable(false)]
        public Icon SmallIcon
        {
            get
            {
                return _smallicon;
            }
        }

        bool _textVisible = true;

        [DefaultValue(true)]
        public bool TextVisible
        {
            get { return _textVisible; }
            set
            {
                if (_textVisible != value)
                {
                    _textVisible = value;
                    PerformLayout();
                    Invalidate();
                }

            }
        }

        Padding _captionMargin = new Padding(3, 1, 3, 1);

        [DefaultValue(typeof(Padding), "3,1,3,1")]
        public virtual Padding CaptionMargin
        {
            get { return _captionMargin; }
            set
            {
                if (!_captionMargin.Equals(value))
                {
                    _captionMargin = value;
                    PerformLayout();
                    Invalidate();
                }
            }
        }

        bool _foldable = true;

        [DefaultValue(true)]
        public virtual bool Foldable
        {
            get { return _foldable; }
            set
            {
                if (_foldable != value)
                {
                    if (!value)
                    {
                       
                        FolderOpened = true;
                    }
                    _foldable = value;
                    TabStop = _foldable;
                    PerformLayout();
                    Invalidate();
                }

            }
        }

        bool _folderOpened = true;

        [DefaultValue(true)]
        public virtual bool FolderOpened
        {
            get
            {
                return _folderOpened;
            }
            set
            {
                if (Foldable)
                {
                    if (_folderOpened != value)
                    {
                        //strTempSearchConditionText = GetUsedSearchText(this);

                        SuspendLayout();

                        foreach (Control c in this.Controls)
                        {
                            c.Visible = value;

                        }
                        _folderOpened = value;


                        // Size = GetPreferredSize(new Size(Width, 0));

                        OnFolderOpenedChanged(EventArgs.Empty);

                        ResumeLayout(false);
                        PerformLayout();

                        if (!_folderOpened)
                        {
                            //閉じた際の処理
                            //子どもがフォーカスを持つ場合、パネル自身に移す
                            if (HasFocusChild(this))
                            {
                                try
                                {
                                    Control p = this;
                                    while (p.Parent != null)
                                    {
                                        p = p.Parent;
                                    }

                                    if (!p.SelectNextControl(this, true, true, true, true))
                                    {
                                        //最悪自分にフォーカス
                                        p.Focus();
                                    }

                                }
                                catch
                                {
                                }
                            }
                        }

                        Invalidate();

                    }
                }
            }
        }

        private bool _captionHover;

        protected virtual bool CaptionHover
        {
            get { return _captionHover; }
            set
            {
                if (_captionHover != value)
                {
                    _captionHover = value;
                    Invalidate();
                }

            }
        }

        bool _emphasis = false;

        [DefaultValue(false)]
        public bool Enphasis
        {
            get { return _emphasis; }
            set
            {
                if (_emphasis != value)
                {
                    _labelSize = CALC_SIZE;
                    _emphasis = value;
                    Invalidate();
                    OnTextChanged(EventArgs.Empty);
                }
            }
        }

        Color _enphasiscolor = Color.Yellow;

        public Color EnphasisColor
        {
            get { return _enphasiscolor; }
            set
            {
                _enphasiscolor = value;
                Invalidate();
            }

        }

        [Browsable(false)]
        public virtual Padding CaptionPadding
        {
            get
            {
                return Padding.Empty;
            }
        }

        [Browsable(false)]
        public virtual bool AddControlOrder
        {
            get
            {
                return false;
            }
        }

        int _labelCharCount = 12;

        [DefaultValue(12)]
        public virtual int LabelCharCount
        {
            get { return _labelCharCount; }
            set
            {
                if (_labelCharCount != value)
                {
                    _labelSize = CALC_SIZE;
                    _labelCharCount = value;
                    Invalidate();
                }


            }
        }

        Size _labelSize = CALC_SIZE;

        protected Size LabelSize
        {
            get
            {
                if (_labelSize.Width < 0)
                {
                    Font f = Font;

                    if (Enphasis)
                    {
                        f = new Font(Font, FontStyle.Bold);
                    }


                    int mk = f.Height;
                    if (mk < 16) { mk = 16; }

                    int lh = CaptionMargin.Vertical + mk;

                    int lw = f.Height * LabelCharCount;



                    if (!string.IsNullOrEmpty(Text))
                    {
                        //マーク以外で１文字分確保　(1/4)マーク(1/4)文字列(1/2)
                        lw = (int)Math.Max(lw, TextRenderer.MeasureText(Text, f).Width + f.Height + mk + CaptionMargin.Horizontal);
                    }
                    else
                    {
                        lw = (int)Math.Max(lw, mk + CaptionMargin.Horizontal);

                    }
                    _labelSize = new Size(lw, lh);
                }
                return _labelSize;
            }
        }

        #endregion

        #region Construct

        public CaptionBasePanel()
        {
            Dock = DockStyle.Top;
            SetStyle(ControlStyles.Selectable, true);
            AutoSize = true;
        }

        #endregion

        #region Override Properties

        public override LayoutEngine LayoutEngine
        {
            get
            {
                return _layout;
            }
        }

        [Localizable(true)]
        [Browsable(true)]
        public override string Text
        {
            get
            {
                return base.Text;
            }
            set
            {
                if (base.Text != value)
                {
                    base.Text = value;
                    PerformLayout();
                }

            }
        }

        [DefaultValue(DockStyle.Top)]
        public override DockStyle Dock
        {
            get
            {
                return base.Dock;
            }
            set
            {
                base.Dock = value;
            }
        }

        [DefaultValue(true)]
        public override bool AutoSize
        {
            get
            {
                return base.AutoSize;
            }
            set
            {
                base.AutoSize = value;
            }
        }

        public override Cursor Cursor
        {
            get
            {
                return (CaptionHover) ? Cursors.Hand : base.Cursor;
            }
            set
            {
                base.Cursor = value;
            }
        }

        #endregion

        #region Fields

        protected CaptionLayout _layout = new CaptionLayout();
        static readonly Size CALC_SIZE = new Size(-1, -1);
        static readonly object FolderOpenedChangedEvent = new object();
        static readonly object IconChangedEvent = new object();
        static readonly object WaitingTickEvent = new object();
        static ImageAttributes _imageatt = null;

        #endregion

        #region Event
        
        public event EventHandler<EventArgs> FolderOpenedChanged
        {
            add
            {
                Events.AddHandler(FolderOpenedChangedEvent, value);
            }
            remove
            {
                Events.RemoveHandler(FolderOpenedChangedEvent, value);
            }
        }
        public event EventHandler IconChanged
        {
            add
            {
                Events.AddHandler(IconChangedEvent, value);
            }
            remove
            {
                Events.RemoveHandler(IconChangedEvent, value);
            }

        }
        public event EventHandler WaitingTick
        {
            add
            {
                Events.AddHandler(WaitingTickEvent, value);
            }
            remove
            {
                Events.RemoveHandler(WaitingTickEvent, value);
            }

        }

        #endregion

        #region Method

        protected virtual void OnFolderOpenedChanged(EventArgs e)
        {
            EventHandler<EventArgs> dlg = (EventHandler<EventArgs>)Events[FolderOpenedChangedEvent];
            if (dlg != null)
            {
                dlg(this, e);
            }
        }
        protected virtual void OnIconChanged(EventArgs e)
        {
            EventHandler dlg = (EventHandler)Events[IconChangedEvent];
            if (dlg != null)
            {
                dlg(this, e);
            }
        }
        protected virtual void OnWaitingTick(EventArgs e)
        {
            EventHandler dlg = (EventHandler)Events[WaitingTickEvent];
            if (dlg != null)
            {
                dlg(this, e);
            }
        }

        protected override void OnMouseDown(MouseEventArgs e)
        {
            if (TabStop)
            {
                try
                {
                    this.Focus();
                }
                catch
                {
                }
            }

            base.OnMouseDown(e);
        }
        protected override void OnGotFocus(EventArgs e)
        {
            Invalidate();
            base.OnGotFocus(e);
        }
        protected override void OnLostFocus(EventArgs e)
        {
            Invalidate();
            base.OnLostFocus(e);
        }
        protected override void OnTextChanged(EventArgs e)
        {
            _labelSize = CALC_SIZE;
            base.OnTextChanged(e);
        }
        protected override void OnFontChanged(EventArgs e)
        {
            _labelSize = CALC_SIZE;
            base.OnFontChanged(e);
        }
        protected override void OnLayout(LayoutEventArgs levent)
        {
            base.OnLayout(levent);
            if (!string.IsNullOrEmpty(Text))
            {
                Invalidate();
            }
        }
        protected override void OnMouseLeave(EventArgs e)
        {
            base.OnMouseLeave(e);
            if (CaptionHover)
            {
                CaptionHover = false;
            }
        }
        protected override void OnControlAdded(ControlEventArgs e)
        {
            e.Control.Visible = FolderOpened;
            base.OnControlAdded(e);
        }
        protected override void OnControlRemoved(ControlEventArgs e)
        {
            base.OnControlRemoved(e);
        }
        protected override void OnKeyPress(KeyPressEventArgs e)
        {
            if (Foldable)
            {
                if (e.KeyChar == ' ' || e.KeyChar == 13)
                {
                    FolderOpened = !FolderOpened;
                }
            }
            base.OnKeyPress(e);
        }
        protected override void Dispose(bool disposing)
        {
            if (this._waittimer != null)
            {
                this._waittimer.Tick -= new EventHandler(this._waittimer_Tick);
                this._waittimer.Dispose();
                this._waittimer = null;
            }
            if (this._icon != null)
            {
                this._icon.Dispose();
                this._icon = null;
            }
            if (this._smallicon != null)
            {
                this._smallicon.Dispose();
                this._smallicon = null;
            }

            if (_imageatt != null)
            {
                _imageatt.Dispose();
                _imageatt = null;
            }
            //this._layout = null;

            base.Dispose(disposing);
        }
        public override Size GetPreferredSize(Size proposedSize)
        {
            return _layout.GetPreferredSize(this, proposedSize);
        }

        private bool HasFocusChild(Control parent)
        {
            if (parent.Focused)
            {
                return true;
            }
            foreach (Control c in parent.Controls)
            {
                if (HasFocusChild(c))
                {
                    return true;
                }
            }
            return false;
        }
        private static ImageAttributes TransparentImageAttribute()
        {
            if (_imageatt == null)
            {
                ColorMatrix cm = new ColorMatrix();
                cm.Matrix00 = 1;
                cm.Matrix11 = 1;
                cm.Matrix22 = 1;
                cm.Matrix33 = 0.4F;
                cm.Matrix44 = 1;

                _imageatt = new System.Drawing.Imaging.ImageAttributes();

                _imageatt.SetColorMatrix(cm);

            }
            return _imageatt;

        }

        public void DrawMarkAndText(Graphics g, Font font, Color markcolor, Color textcolor, Point pos)
        {
            DrawMarkAndText(g, font, markcolor, textcolor, pos, false);
        }
        public void DrawMarkAndText(Graphics g, Font font, Color markcolor, Color textcolor, Point pos, bool harftrans)
        {
            DrawMarkAndText(g, font, markcolor, textcolor, new Rectangle(pos, Size.Empty), harftrans, string.Empty);
        }
        public void DrawMarkAndText(Graphics g, Font font, Color markcolor, Color textcolor, Rectangle rect, bool harftrans, string option)
        {
            Font f = font;

            if (Enphasis)
            {
                f = new Font(font, FontStyle.Bold);
            }


            int fh = f.Height;
            int mk = fh;
            if (mk < 16) { mk = 16; }

            int r = mk / 2;
            Point p = new Point(rect.Location.X + r + fh / 4, rect.Location.Y + r);

            if (this.Waiting)
            {
                if (this.SmallIcon != null)
                {

                    Rectangle mr = new Rectangle(p.X - 8, p.Y - 8, 16, 16);
                    g.DrawImage(this.SmallIcon.ToBitmap(), mr, 0, 0, 16, 16, GraphicsUnit.Pixel, TransparentImageAttribute());
                }

                for (int i = 0; i < 8; i++)
                {
                    double rr = Math.PI / 4 * ((i + WaitCounter) % 8);
                    double cr = r - 2;

                    PointF cp = new PointF(p.X + (float)(Math.Cos(rr) * cr), p.Y + (float)(Math.Sin(rr) * cr));
                    g.FillEllipse(new SolidBrush(Color.FromArgb(32 * i + 31, markcolor)), new RectangleF(cp.X - 1, cp.Y - 1, 3, 3));
                }

            }
            else
            {
                if (this.SmallIcon != null)
                {
                    Rectangle mr = new Rectangle(p.X - 8, p.Y - 8, 16, 16);
                    if (harftrans)
                    {
                        g.DrawImage(this.SmallIcon.ToBitmap(), mr, 0, 0, 16, 16, GraphicsUnit.Pixel, TransparentImageAttribute());
                    }
                    else
                    {
                        g.DrawIcon(this.SmallIcon, mr);
                    }
                }
                else
                {
                    Rectangle mr = new Rectangle(p.X - fh / 2, p.Y - fh / 2, fh, fh);
                    g.FillEllipse(new SolidBrush(markcolor), mr);
                }
            }

            int head = mk + fh / 2;
            //Y方向の+1はTextRendererをAntiAlias描画に合わせた誤差修正値
            Point tp = new Point(rect.Location.X + head, rect.Location.Y + (mk - fh) / 2);

            string dtext = this.Text;

            //オプションがある場合
            if (!string.IsNullOrEmpty(option))
            {
                dtext = dtext + " - " + option;
            }

            if (rect.Size.Width == 0)
            {
                //TextRenderer.DrawText(g, dtext, Font, tp, textcolor);

                if (Enphasis)
                {
                    Brush sb = new SolidBrush(Color.FromArgb(30, Color.Black));
                    for (int x = -1; x < 2; x++)
                    {
                        for (int y = -1; y < 2; y++)
                        {
                            Point sp = tp;
                            sp.Offset(x, y);
                            g.DrawString(dtext, f, sb, sp);
                        }
                    }

                    //*** Add Sta BugFix:0004187 2009/06/01 nitta
                    sb.Dispose();
                    sb = null;
                    //*** Add End BugFix:0004187 2009/06/01 nitta

                    g.DrawString(dtext, f, new SolidBrush(EnphasisColor), tp);
                }
                else
                {
                    g.DrawString(dtext, f, new SolidBrush(textcolor), tp);
                }

            }
            else
            {
                Rectangle trect = new Rectangle(tp, new Size(rect.Width - head, f.Height));
                //                TextRenderer.DrawText(g, dtext, Font, trect, textcolor,Color.Transparent,TextFormatFlags.EndEllipsis);
                StringFormat sf = new StringFormat();
                sf.Trimming = StringTrimming.EllipsisWord;

                if (Enphasis)
                {
                    Brush sb = new SolidBrush(Color.FromArgb(30, Color.Black));
                    for (int x = -1; x < 2; x++)
                    {
                        for (int y = -1; y < 2; y++)
                        {
                            Rectangle srect = trect;
                            srect.Offset(x, y);
                            g.DrawString(dtext, f, sb, srect, sf);
                        }
                    }

                    //*** Add Sta BugFix:0004187 2009/06/01 nitta
                    sb.Dispose();
                    sb = null;
                    //*** Add End BugFix:0004187 2009/06/01 nitta

                    g.DrawString(dtext, f, new SolidBrush(EnphasisColor), trect, sf);
                }
                else
                {
                    g.DrawString(dtext, f, new SolidBrush(textcolor), trect, sf);
                }

                //*** Add Sta BugFix:0004187 2009/06/01 nitta
                sf.Dispose();
                sf = null;
                //*** Add End BugFix:0004187 2009/06/01 nitta

            }

            //*** Add Sta BugFix:0004187 2009/06/01 nitta
            //f.Dispose();
            f = null;
            //*** Add End BugFix:0004187 2009/06/01 nitta

        }

        #endregion


    }
}
