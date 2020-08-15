using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace Common.BaseUI
{
    public partial class BaseForm : BaseDesignForm
    {
        public BaseForm()
        {
            InitializeComponent();
        }

        public override string Text
        {
            get
            {
                return base.Text;
            }
            set
            {
                base.Text = value;
                this.lblTitle.Text = value;
            }
        }

        public bool MaxButtonVisable 
        {
            get { return this.btnMax.Visible; }
            set 
            {
                if (this.btnMax.Visible != value && this.flpCaptionButton.Width != 60)
                {
                    this.btnMax.Visible = value;
                    this.SetCaptionButtonVisible(this.btnMax, value);
                }
                else 
                {
                    this.btnMax.Visible = value;
                }
                
            }
        }
        
        public bool MinButtonVisable 
        {
            get { return this.btnMin.Visible; }
            set
            {
                if (this.btnMin.Visible != value && this.flpCaptionButton.Width != 60)
                {
                    this.btnMin.Visible = value;
                    this.SetCaptionButtonVisible(this.btnMin, value);
                }
                else 
                {
                    this.btnMin.Visible = value;
                }
            }
        }

        public bool CaptionVisable 
        {
            get { return this.pnlCaption.Visible; }
            set { this.pnlCaption.Visible = value; }
        }

        Point downPoint = Point.Empty;

        protected void OnMouseDown(object sender, MouseEventArgs e)
        {
            if (e.Button != MouseButtons.Left) return;
            //this.WindowState = FormWindowState.Normal;
            downPoint = new Point(e.X, e.Y);

        }

        protected void OnMouseUp(object sender, MouseEventArgs e)
        {
            if (e.Button != MouseButtons.Left) return;
            downPoint = Point.Empty;
        }

        protected void OnMouseMove(object sender, MouseEventArgs e)
        {
            if (downPoint == Point.Empty) return;
            Point loc = new Point(this.Left + e.X - downPoint.X, this.Top + e.Y - downPoint.Y);
            this.Location = loc;
        }

        protected void OnClose(object sender, EventArgs e)
        {
            this.Close();
        }

        protected void OnMinimized(object sender, EventArgs e)
        {
            this.WindowState = FormWindowState.Minimized;
        }

        protected void OnMaximized(object sender, EventArgs e)
        {
            
            if (this.WindowState != FormWindowState.Maximized)
            {
               
                this.WindowState = FormWindowState.Maximized;
               
                
            }
            else 
            {
                this.WindowState = FormWindowState.Normal;
            }
            
        }

        protected void SetCaptionButtonVisible(Control ctr, bool Visible) 
        {
            int nWidth = ctr.Width + ctr.Margin.Left + ctr.Margin.Right;
            if (Visible)
            {
                ctr.Parent.Width += nWidth;
                ctr.Visible = Visible;
            }
            else 
            {
                ctr.Parent.Width -= nWidth;
                ctr.Visible = Visible;
            }
        }

    }
}
