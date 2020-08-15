using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Drawing;
using System.Windows.Forms;
using System.ComponentModel;
using Common;

namespace Common.BaseControl
{
    public class DesignablePanel : Panel, IDesignable, IEditable
    {

        public DesignablePanel()
        {

        }

        #region DesignablePanel

        private bool _autoTabOrder = true;

        [DefaultValue(true)]
        public bool AutoTabOrder
        {
            get { return _autoTabOrder; }
            set
            {
                if (_autoTabOrder != value)
                {
                    _autoTabOrder = value;
                    if (_autoTabOrder)
                    {
                        TabReset();
                    }
                }
            }
        }

        protected virtual void TabReset()
        {
            int st = 0;
            int ed = Controls.Count * 2 - 1;
            for (int i = Controls.Count - 1; i >= 0; i--)
            {
                switch (Controls[i].Dock)
                {
                    case DockStyle.Right:
                    case DockStyle.Bottom:
                        Controls[i].TabIndex = ed;
                        ed--;
                        break;
                    default:
                        Controls[i].TabIndex = st;
                        st++;
                        break;
                }
            }
        }

        #endregion

        #region Override Panel

        public override Color BackColor
        {
            get
            {
                return base.BackColor;
            }
            set
            {
                base.BackColor = value;
            }
        }

        protected override Padding DefaultMargin
        {
            get
            {
                return Padding.Empty;
            }
        }

        protected override void OnControlAdded(ControlEventArgs e)
        {
            IEditable we = e.Control as IEditable;
            if (we != null)
            {
                we.OnParentEditableChanged(EventArgs.Empty);
            }
            base.OnControlAdded(e);
        }

        protected override void OnLayout(LayoutEventArgs levent)
        {
            if (AutoTabOrder)
            {
                TabReset();
            }
            base.OnLayout(levent);
        }

        protected override void OnParentEnabledChanged(EventArgs e)
        {
            base.OnParentEnabledChanged(e);
            Invalidate();
        }

        protected override void Dispose(bool disposing)
        {
            if (this._labelFont != null)
            {
                this._labelFont.Dispose();
                this._labelFont = null;
            }

            base.Dispose(disposing);
        }

        #endregion

        #region Implement IDesignable

        private Color _labelForeColor = Color.Empty;

        public Color LabelForeColor
        {
            get
            {
                if (_labelForeColor != Color.Empty)
                {
                    return _labelForeColor;
                }
                else
                {
                    IDesignable lp = Parent as IDesignable;
                    return (lp != null) ? lp.LabelForeColor : CaptionGraphics.BlendColor(ButtonLabelBackColor, Color.Black, 80);
                }
            }
            set
            {
                _labelForeColor = value;
                Refresh();
            }
        }

        private Color _gotFocusLabelForeColor = Color.Empty;

        public Color GotFocusLabelForeColor
        {
            get
            {
                if (_gotFocusLabelForeColor != Color.Empty)
                {
                    return _gotFocusLabelForeColor;
                }
                else
                {
                    IDesignable lp = Parent as IDesignable;
                    return (lp != null) ? lp.GotFocusLabelForeColor : Color.Brown;
                }
            }
            set
            {
                _gotFocusLabelForeColor = value;
            }
        }

        private Color _buttonLabelBackColor = Color.Empty;

        public Color ButtonLabelBackColor
        {
            get
            {
                if (!Enabled)
                {
                    return Color.Silver;
                }

                if (_buttonLabelBackColor != Color.Empty)
                {
                    return _buttonLabelBackColor;
                }
                else
                {
                    IDesignable lp = Parent as IDesignable;
                    return (lp != null) ? lp.ButtonLabelBackColor : Color.Green;
                }
            }
            set
            {
                _buttonLabelBackColor = value;
                Refresh();

            }
        }

        private Color _buttonLabelForeColor = Color.Empty;

        public Color ButtonLabelForeColor
        {
            get
            {
                if (_buttonLabelForeColor != Color.Empty)
                {
                    return _buttonLabelForeColor;
                }
                else
                {
                    IDesignable lp = Parent as IDesignable;
                    return (lp != null) ? lp.ButtonLabelForeColor : Color.White;
                }
            }
            set
            {
                _buttonLabelForeColor = value;
                Refresh();

            }
        }

        private Color _buttonLabelFocusBackColor = Color.Empty;

        public Color ButtonLabelFocusBackColor
        {
            get
            {
                if (_buttonLabelFocusBackColor != Color.Empty)
                {
                    return _buttonLabelFocusBackColor;
                }
                else
                {
                    IDesignable lp = Parent as IDesignable;
                    return (lp != null) ? lp.ButtonLabelFocusBackColor : Color.Orange;
                }
            }
            set
            {
                _buttonLabelFocusBackColor = value;

            }
        }

        private Color _gotFocusBackColor = Color.Empty;

        public Color GotFocusBackColor
        {
            get
            {
                if (_gotFocusBackColor != Color.Empty)
                {
                    return _gotFocusBackColor;
                }
                else
                {
                    IDesignable lp = Parent as IDesignable;
                    return (lp != null) ? lp.GotFocusBackColor : Color.LemonChiffon;
                }
            }
            set
            {
                _gotFocusBackColor = value;
                Refresh();
            }
        }

        private Color _errorBackColor = Color.Empty;

        public Color ErrorBackColor
        {
            get
            {
                if (_errorBackColor != Color.Empty)
                {
                    return _errorBackColor;
                }
                else
                {
                    IDesignable lp = Parent as IDesignable;
                    return (lp != null) ? lp.ErrorBackColor : Color.SeaShell;
                }
            }
            set
            {
                _errorBackColor = value;
                Refresh();
            }
        }

        private Color _warningBackColor = Color.Empty;

        public Color WarningBackColor
        {
            get
            {
                if (_warningBackColor != Color.Empty)
                {
                    return _warningBackColor;
                }
                else
                {
                    IDesignable lp = Parent as IDesignable;
                    return (lp != null) ? lp.WarningBackColor : Color.LightYellow;
                }
            }
            set
            {
                _warningBackColor = value;
                Refresh();
            }
        }

        private Font _labelFont = null;

        public Font LabelFont
        {
            get
            {
                if (_labelFont != null)
                {
                    return _labelFont;
                }
                else
                {
                    IDesignable lp = Parent as IDesignable;
                    return (lp != null) ? lp.LabelFont : Font;
                }
            }
            set
            {
                _labelFont = value;
                OnLabelFontChanged(EventArgs.Empty);

            }
        }

        public virtual void OnLabelFontChanged(EventArgs e)
        {
            foreach (Control c in Controls)
            {
                IDesignable lp = c as IDesignable;
                if (lp != null)
                {
                    lp.OnParentLabelFontChanged(e);
                }
            }
            PerformLayout();
            Invalidate();

        }

        public virtual void OnParentLabelFontChanged(EventArgs e)
        {
            if (_labelFont == null)
            {
                this.OnLabelFontChanged(e);
            }
        }

        #endregion

        #region Implement IEditable

        private bool _editable = true;

        public bool Editable
        {
            get
            {
                if (!_editable)
                {
                    return false;
                }
                else
                {
                    IEditable ep = Parent as IEditable;
                    return (ep != null) ? ep.Editable : true;
                }
            }
            set
            {
                if (_editable != value)
                {
                    SuspendLayout();
                    _editable = value;
                    OnEditableChanged(EventArgs.Empty);
                    ResumeLayout(false);
                    PerformLayout();
                }
            }
        }

        public virtual void OnEditableChanged(EventArgs e)
        {
            foreach (Control c in Controls)
            {
                IEditable ep = c as IEditable;
                if (ep != null)
                {
                    ep.OnParentEditableChanged(e);
                }
            }
            Invalidate();

        }

        public virtual void OnParentEditableChanged(EventArgs e)
        {
            this.OnEditableChanged(e);
        }

        #endregion

    }
}
