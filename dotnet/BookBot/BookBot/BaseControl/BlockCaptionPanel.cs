using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Drawing;
using System.ComponentModel;
using System.Drawing.Drawing2D;
using Common;

namespace Common.BaseControl
{
    [DefaultProperty("Text")]
    public class BlockCaptionPanel : CaptionBasePanel, ICaptionPanel
    {

        private ColorTimer _timer;
        private static Padding _def_innermargin = new Padding(10, 8, 10, 8);

        public BlockCaptionPanel()
        {
            DoubleBuffered = true;
            _timer = new ColorTimer();
            _timer.ColorChange += new EventHandler(_timer_ColorChange);
            _timer.ColorChangeStop += new EventHandler(_timer_ColorChangeStop);
            CaptionMargin = new Padding(4, 2, 4, 2);
        }


        protected override void Dispose(bool disposing)
        {
            if (this._timer != null)
            {
                this._timer.ColorChange -= new EventHandler(this._timer_ColorChange);
                this._timer.ColorChangeStop -= new EventHandler(this._timer_ColorChangeStop);
                this._timer.Dispose();
                this._timer = null;
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

        private Color _backColor = Color.Empty;


        public override Color BackColor
        {
            get
            {
                if (ShouldSerializeBackColor())
                {
                    return _backColor;
                }
                else
                {
                    if (Parent != null)
                    {

                        return CaptionGraphics.BlendColor(Color.White, ButtonLabelBackColor, 95);
                    }
                    else
                    {
                        return ButtonLabelBackColor;
                    }
                }
            }
            set
            {
                _backColor = value;
                OnBackColorChanged(EventArgs.Empty);
                //Refresh();
            }
        }

        /// <summary>
        /// BackColorのデフォルト確認
        /// </summary>
        /// <returns></returns>
        private bool ShouldSerializeBackColor()
        {
            return _backColor != Color.Empty;
        }

        /// <summary>
        /// BackColorのリセット処理
        /// </summary>
        public override void ResetBackColor()
        {
            _backColor = Color.Empty;
            OnBackColorChanged(EventArgs.Empty);
            //Refresh();
        }


        private bool _captionHover;

        /// <summary>
        /// キャプションの上にカーソルがあるかを設定する内部処理用プロパティ
        /// </summary>
        protected override bool CaptionHover
        {
            get { return _captionHover; }
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
                        _timer.Start(ButtonLabelBackColor);
                    }
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
                if (!TextVisible)
                {
                    return _def_innermargin;
                }
                else
                {
                    if (string.IsNullOrEmpty(Text) && (!Foldable))
                    {
                        return _def_innermargin;
                    }
                    else
                    {
                        return new Padding(0, LabelSize.Height, 0, 0) + _def_innermargin;
                    }

                }

            }
        }


        [DefaultValue(typeof(Padding), "4,2,4,2")]
        public override Padding CaptionMargin
        {
            get
            {
                return base.CaptionMargin;
            }
            set
            {
                base.CaptionMargin = value;
            }
        }

        private string _information = string.Empty;

        [DefaultValue("")]
        public string Information
        {
            get { return _information; }
            set
            {
                if (_information != value)
                {
                    _information = value;
                    Invalidate();
                }
            }
        }

        private bool _informationClearable = true;

        [DefaultValue(true)]
        public bool InformationClearable
        {
            get { return _informationClearable; }
            set { _informationClearable = value; }
        }

        protected override void OnPaintBackground(PaintEventArgs e)
        {


            //必要な部分の再描画

            if (!TextVisible)
            {
                e.Graphics.FillRectangle(new SolidBrush(BackColor), e.ClipRectangle);
            }
            else
            {

                if (Parent != null)
                {
                    e.Graphics.FillRectangle(new SolidBrush((Parent.BackColor != Color.Transparent) ? Parent.BackColor : BackColor), e.ClipRectangle);
                }
                else
                {
                    base.OnPaintBackground(e);
                }
            }
        }

        protected override void OnScroll(ScrollEventArgs se)
        {
            base.OnScroll(se);
            Invalidate();
        }


        /// <summary>
        /// 描画処理の拡張
        /// </summary>
        /// <param name="e"></param>
        protected override void OnPaint(PaintEventArgs e)
        {
            Brush brush = new SolidBrush(ClickableColor);
            //Pen pen = new Pen(ButtonLabelFocusBackColor);
            Color fc = ButtonLabelForeColor;
            Color mfc = ButtonLabelForeColor;

            e.Graphics.SmoothingMode = SmoothingMode.AntiAlias;

            int clheight = DisplayRectangle.Height;
            int clwidth = DisplayRectangle.Width;
            e.Graphics.TranslateTransform(DisplayRectangle.X, DisplayRectangle.Y);


            if (TextVisible)
            {
                if (!string.IsNullOrEmpty(Text) || Foldable)
                {


                    if (Foldable)
                    {
                        GraphicsPath gp;

                        if (FolderOpened)
                        {



                            Rectangle r = new Rectangle(6, LabelSize.Height + 6 - 4, clwidth - 12, clheight - LabelSize.Height - 12 + 4);
                            GraphicsPath gpf = CaptionGraphics.CreateRoundRectPath(r, 4, 4, false, true, true, true);

                            CaptionGraphics.DrawShadow(e.Graphics, gpf);

                            e.Graphics.FillPath(new SolidBrush(BackColor), gpf);

                            Pen p = new Pen(ButtonLabelBackColor, 2);
                            e.Graphics.DrawPath(p, gpf);

                            gp = CaptionGraphics.CreateRoundRectPath(new Rectangle(6, 2, LabelSize.Width, LabelSize.Height), 4, 4, true, true, false, false);
                            e.Graphics.FillPath(brush, gp);
                            e.Graphics.DrawPath(new Pen(brush, 2), gp);

                            DrawMarkAndText(e.Graphics, Font, (Focused) ? ButtonLabelFocusBackColor : mfc, fc, new Point(CaptionMargin.Left + 6, CaptionMargin.Top + 2));


                        }
                        else
                        {

                            Rectangle r = new Rectangle(6, 2, clwidth - 12, LabelSize.Height);
                            gp = CaptionGraphics.CreateRoundRectPath(r, 4, 4, true, true, true, true);
                            CaptionGraphics.DrawShadow(e.Graphics, gp);

                            e.Graphics.FillPath(brush, gp);

                            Pen p = new Pen(brush, 2);
                            e.Graphics.DrawPath(p, gp);

                            CaptionInformationEventArgs ev = new CaptionInformationEventArgs(Information);
                            OnCaptionInformation(ev);

                            DrawMarkAndText(e.Graphics, Font, (Focused) ? ButtonLabelFocusBackColor : mfc, fc, new Rectangle(CaptionMargin.Left + 6, CaptionMargin.Top + 2, clwidth - 12 - CaptionMargin.Horizontal, LabelSize.Height), false, ev.Information);

                        }


                    }
                    else
                    {
                        Rectangle r = new Rectangle(6, 2, clwidth - 12, clheight - 8);
                        GraphicsPath gpf = CaptionGraphics.CreateRoundRectPath(r, 4, 4, true, true, true, true);
                        CaptionGraphics.DrawShadow(e.Graphics, gpf);

                        e.Graphics.FillPath(new SolidBrush(BackColor), gpf);

                        Pen p = new Pen(ButtonLabelBackColor, 2);
                        e.Graphics.DrawPath(p, gpf);


                        GraphicsPath gp = CaptionGraphics.CreateRoundRectPath(new Rectangle(6, 2, clwidth - 12, LabelSize.Height), 4, 4, true, true, false, false);
                        e.Graphics.FillPath(brush, gp);

                        CaptionInformationEventArgs ev = new CaptionInformationEventArgs(Information);
                        OnCaptionInformation(ev);


                        //DrawMarkAndText(e.Graphics, Font, (Focused) ? ButtonLabelFocusBackColor : mfc, fc, new Point(CaptionMargin.Left + 6, CaptionMargin.Top + 2));
                        DrawMarkAndText(e.Graphics, Font, (Focused) ? ButtonLabelFocusBackColor : mfc, fc, new Rectangle(CaptionMargin.Left + 6, CaptionMargin.Top + 2, clwidth - 12 - CaptionMargin.Horizontal, LabelSize.Height), false, ev.Information);

                    }



                }
                else
                {
                    Rectangle r = new Rectangle(6, 6, clwidth - 12, clheight - 12);
                    GraphicsPath gpf = CaptionGraphics.CreateRoundRectPath(r, 4, 4, true, true, true, true);
                    CaptionGraphics.DrawShadow(e.Graphics, gpf);

                    e.Graphics.FillPath(new SolidBrush(BackColor), gpf);

                    Pen p = new Pen(ButtonLabelBackColor, 2);
                    e.Graphics.DrawPath(p, gpf);

                }
            }
            e.Graphics.TranslateTransform(-DisplayRectangle.X, -DisplayRectangle.Y);

            base.OnPaint(e);
        }

        protected override void OnMouseMove(MouseEventArgs e)
        {

            base.OnMouseMove(e);
            if (Foldable && TextVisible)
            {
                if (e.Y < CaptionPadding.Top && (e.X < LabelSize.Width || (!FolderOpened)))
                {
                    CaptionHover = true;
                    return;
                }
            }
            CaptionHover = false;

        }


        protected override void OnMouseClick(MouseEventArgs e)
        {
            base.OnMouseClick(e);
            if (e.Button == MouseButtons.Left)
            {
                if (Foldable && TextVisible)
                {
                    if (e.Y < CaptionPadding.Top && (e.X < LabelSize.Width || (!FolderOpened)))
                    {
                        FolderOpened = !FolderOpened;
                    }
                }
            }
        }


        private static readonly object CaptionInformationEvent = new object();

        public event EventHandler<CaptionInformationEventArgs> CaptionInformation
        {
            add
            {
                Events.AddHandler(CaptionInformationEvent, value);
            }
            remove
            {
                Events.RemoveHandler(CaptionInformationEvent, value);
            }
        }

        protected virtual void OnCaptionInformation(CaptionInformationEventArgs e)
        {
            EventHandler<CaptionInformationEventArgs> dlg = (EventHandler<CaptionInformationEventArgs>)Events[CaptionInformationEvent];
            if (dlg != null)
            {
                dlg(this, e);
            }
        }

        public class CaptionInformationEventArgs : EventArgs
        {
            public CaptionInformationEventArgs(string infomation)
            {
                _information = infomation;
            }

            private string _information = string.Empty;

            public string Information
            {
                get { return _information; }
                set { _information = value; }
            }
        }


    }
}
