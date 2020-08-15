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
    public partial class DrawingToScreen : Form
    {
        public DrawingToScreen()
        {
            InitializeComponent();
        }

        bool drawEllipse = false;

        private void button1_Click(object sender, EventArgs e)
        {
            this.drawEllipse = !this.drawEllipse;
            using (Graphics g = this.CreateGraphics()) 
            {
                if (drawEllipse)
                {
                    g.FillEllipse(Brushes.DarkBlue, this.ClientRectangle);
                }
                else 
                {
                    g.FillEllipse(SystemBrushes.Control, this.ClientRectangle);
                }
            }
        }
    }
}
