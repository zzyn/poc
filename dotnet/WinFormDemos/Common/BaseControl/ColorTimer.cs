using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Drawing;

namespace Common.BaseControl
{
    public class ColorTimer : Timer
    {
        private Color _toColor = Color.Empty;
        private Color _fromColor = Color.Empty;

        private int _animationPer = 0;
        private bool _roop = false;


        public ColorTimer()
        {
            Interval = 50;
        }

        private Color _nowColor;

        public Color Color
        {
            get { return _nowColor; }
            set
            {
                _nowColor = value;
                OnColorChange(EventArgs.Empty);

            }
        }

        private readonly static object ColorChangeEvent = new object();

        public event EventHandler ColorChange
        {
            add
            {
                Events.AddHandler(ColorChangeEvent, value);
            }
            remove
            {
                Events.RemoveHandler(ColorChangeEvent, value);
            }

        }
        protected virtual void OnColorChange(EventArgs e)
        {
            EventHandler dlg = (EventHandler)Events[ColorChangeEvent];
            if (dlg != null)
            {
                dlg(this, e);
            }
        }
        private readonly static object ColorChangeStopEvent = new object();

        public event EventHandler ColorChangeStop
        {
            add
            {
                Events.AddHandler(ColorChangeStopEvent, value);
            }
            remove
            {
                Events.RemoveHandler(ColorChangeStopEvent, value);
            }

        }
        protected virtual void OnColorChangeStop(EventArgs e)
        {
            EventHandler dlg = (EventHandler)Events[ColorChangeStopEvent];
            if (dlg != null)
            {
                dlg(this, e);
            }
        }



        protected override void OnTick(EventArgs e)
        {
            base.OnTick(e);

            _animationPer += 25;
            if (_animationPer > 100)
            {
                _animationPer = 0;
                if (_roop)
                {
                    Color = _toColor;
                    Color c = _toColor;
                    _toColor = _fromColor;
                    _fromColor = c;

                }
                else
                {
                    Color = _toColor;
                    Enabled = false;
                    OnColorChangeStop(EventArgs.Empty);
                }

            }
            else
            {
                Color c = CaptionGraphics.BlendColor(_toColor, _fromColor, _animationPer);
                Color = c;
            }

        }
        public void Start(Color toColor)
        {
            _fromColor = Color;
            _toColor = toColor;
            _roop = false;
            _animationPer = 0;
            Enabled = true;
        }
        public void RoopStart(Color color, Color color2)
        {
            Color = color;
            _fromColor = color;
            _toColor = color2;
            _roop = true;
            _animationPer = 0;
            Enabled = true;
        }
        public void Stop(Color nowColor)
        {
            Color = nowColor;
            Enabled = false;
        }




    }
}
