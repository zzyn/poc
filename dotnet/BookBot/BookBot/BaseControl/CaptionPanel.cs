using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.ComponentModel;
using System.Windows.Forms;
using System.Drawing;
using System.Drawing.Drawing2D;
using Common;

namespace Common.BaseControl
{
    [DefaultProperty("Text")]
    public class CaptionPanel : CaptionBasePanel, ICaptionPanel
    {
        private ColorTimer _timer;

        private static Padding _def_innermargin = new Padding(12, 3, 8, 8);
        private static Padding _def_simpleinnermargin = new Padding(4);

        public CaptionPanel()
        {
            DoubleBuffered = true;
            _timer = new ColorTimer();
            _timer.ColorChange += new EventHandler(_timer_ColorChange);
            _timer.ColorChangeStop += new EventHandler(_timer_ColorChangeStop);

        }


        protected override void Dispose(bool disposing)
        {
            if (_timer != null)
            {
                _timer.ColorChange -= new EventHandler(_timer_ColorChange);
                _timer.ColorChangeStop -= new EventHandler(_timer_ColorChangeStop);
                _timer.Dispose();
                _timer = null;
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


        private bool _captionHover;


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




        [Browsable(false)]
        public override Padding CaptionPadding
        {
            get
            {
                if (!TextVisible)
                {
                    return _def_simpleinnermargin;
                }
                else
                {
                    return new Padding(0, LabelSize.Height, 0, 0) + _def_innermargin;
                }
            }
        }

        protected override void OnPaintBackground(PaintEventArgs e)
        {
            base.OnPaintBackground(e);
        }




        protected override void OnPaint(PaintEventArgs e)
        {

            int clheight = DisplayRectangle.Height;
            int clwidth = DisplayRectangle.Width;
            e.Graphics.TranslateTransform(DisplayRectangle.X, DisplayRectangle.Y);


            if (TextVisible)
            {


                Brush brush = new SolidBrush(ClickableColor);
                //Pen pen = new Pen(ButtonLabelFocusBackColor);
                Color fc = ButtonLabelForeColor;
                Color mfc = ButtonLabelForeColor;
                e.Graphics.SmoothingMode = SmoothingMode.AntiAlias;

                if (Foldable)
                {
                    GraphicsPath gp;

                    if (FolderOpened)
                    {
                        Pen pn = new Pen(ButtonLabelBackColor, 2);
                        e.Graphics.DrawLine(pn, new Point(LabelSize.Width - 2, LabelSize.Height / 2), new Point(clwidth - CaptionPadding.Right, LabelSize.Height / 2));
                        gp = CaptionGraphics.CreateRoundRectPath(new Rectangle(0, 0, LabelSize.Width, LabelSize.Height), LabelSize.Height / 2, LabelSize.Height / 2);
                        e.Graphics.FillPath(brush, gp);

                    }
                    else
                    {
                        gp = CaptionGraphics.CreateRoundRectPath(new Rectangle(0, 0, clwidth, LabelSize.Height), LabelSize.Height / 2, LabelSize.Height / 2);
                        e.Graphics.FillPath(brush, gp);

                    }

                }
                else
                {

                    fc = CaptionGraphics.BlendColor(ButtonLabelBackColor, Color.Black, 80);
                    mfc = ButtonLabelBackColor;
                    CaptionGraphics.FillRoundRect(e.Graphics, brush, new Rectangle(LabelSize.Width, LabelSize.Height / 2 - 1, clwidth - LabelSize.Width - 2, 2), 2, 2);
                }

                DrawMarkAndText(e.Graphics, Font, (Focused) ? ButtonLabelFocusBackColor : mfc, fc, new Point(CaptionMargin.Left, CaptionMargin.Top));

                // e.Graphics.DrawRectangle(Pens.Red, new Rectangle(0, 0, LabelSize.Width, LabelSize.Height));

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


    }
}
