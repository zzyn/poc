using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Drawing.Drawing2D;

namespace Demos
{
    public partial class PenAlignmentDemo : Form
    {
        public PenAlignmentDemo()
        {
            InitializeComponent();
            this.SetStyle(ControlStyles.ResizeRedraw, true);
        }

        private void PenAlignmentDemo_Paint(object sender, PaintEventArgs e)
        {
            Graphics g = e.Graphics;

            using (Pen pen = new Pen(Color.Red, 12))
            {
                pen.Alignment = PenAlignment.Center;

                g.DrawEllipse(pen, new Rectangle(50, 50, 100, 50));

                pen.Alignment = PenAlignment.Inset;

                g.DrawEllipse(pen, new Rectangle(50, 150, 100, 50));

            }
        }
    }
}
