using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using Common;
using Common.BaseControl;

namespace Common.BaseUI
{
    public partial class BaseDesignForm : Form, IDesignable, IEditable
    {
        public BaseDesignForm()
        {
            tmr = new Timer();
            tmr.Tick += new EventHandler(tmr_Tick);
            tmr.Interval = 200;
            InitializeComponent();
        }

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

        private Timer tmr = null;

        protected override void OnControlAdded(ControlEventArgs e)
        {
            IEditable we = e.Control as IEditable;
            if (we != null)
            {

                {
                    we.OnParentEditableChanged(EventArgs.Empty);
                }
            }
            base.OnControlAdded(e);

        }

        protected override void OnResize(EventArgs e)
        {
            SuspendLayout();
            base.OnResize(e);
            ResumeLayout(false);
            
            if (tmr.Enabled)
            {
                tmr.Stop();
            }
            tmr.Start();
        }

        protected override void Dispose(bool disposing)
        {
            if (this.tmr != null)
            {
                this.tmr.Tick -= new EventHandler(tmr_Tick);
                this.tmr.Dispose();
                this.tmr = null;
            }
            if (this._labelFont != null)
            {
                this._labelFont.Dispose();
                this._labelFont = null;
            }
            base.Dispose(disposing);
        }

        private void tmr_Tick(object sender, EventArgs e)
        {
            tmr.Stop();
            FullPerformLayout(this);
        }

        private void InitializeComponent()
        {
            this.SuspendLayout();
            // 
            // BaseDesignForm
            // 
            this.ClientSize = new System.Drawing.Size(284, 262);
            this.Name = "BaseDesignForm";
            this.ResumeLayout(false);

        }

        public void FullPerformLayout(Control parent)
        {
            parent.PerformLayout();
            foreach (Control c in parent.Controls)
            {
                FullPerformLayout(c);
            }
        }

    }
}
