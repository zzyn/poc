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
    public partial class DashesDemo : Form
    {
        public DashesDemo()
        {
            InitializeComponent();
            this.SetStyle(ControlStyles.ResizeRedraw, true);
        }

        private void DashesDemo_Paint(object sender, PaintEventArgs e)
        {
            Graphics g = e.Graphics;

            using (Pen pen = new Pen(Color.Black, 12)) 
            {
                pen.DashStyle = DashStyle.Custom;

                pen.DashPattern = new float[] { 1f, 1f, 2f, 1f, 3f, 1f, 4f, 1f };

                g.DrawLine(pen, 10, 10, 1024, 10);

            }

            using (Pen pen = new Pen(Color.Black, 20))
            {
                pen.CompoundArray = new float[]{0.0f,0.25f,0.45f,0.55f,0.75f,1.0f};

                g.DrawRectangle(pen, new Rectangle(50, 50, 100, 50));

            }
        }


    }
}
