using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace Demos
{
    public partial class HandlingThePaintEvent : Form
    {
        public HandlingThePaintEvent()
        {
            InitializeComponent();
            // when changing the size of form trigger paint event
            this.SetStyle(ControlStyles.ResizeRedraw, true);
        }

        bool drawEllipse = false;

        private void HandlingThePaintEvent_Paint(object sender, PaintEventArgs e)
        {
            if (!this.drawEllipse) return;
            Graphics g = e.Graphics;
            
            g.FillEllipse(Brushes.DarkBlue, this.ClientRectangle);
        }

        private void button1_Click(object sender, EventArgs e)
        {
            this.drawEllipse = !this.drawEllipse;
            //triggering the paint event
            this.Invalidate(true);
            this.Update();

            //this.Refresh();
            //this.Refresh() = this.Invalidate(true); + this.Update();

        }
    }
}
