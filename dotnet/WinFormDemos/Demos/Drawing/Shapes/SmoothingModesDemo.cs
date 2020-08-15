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
    public partial class SmoothingModesDemo : Form
    {
        public SmoothingModesDemo()
        {
            InitializeComponent();
            this.SetStyle(ControlStyles.ResizeRedraw, true);
        }

        private void SmoothingModes_Paint(object sender, PaintEventArgs e)
        {
            Graphics g = e.Graphics;


            using (Pen pen = new Pen(Color.Red, 12))
            {
                Point[] points = new Point[] { new Point(50, 50), new Point(100, 50), new Point(50, 100), new Point(100, 100) };
                g.DrawCurve(pen, points, 0.5f);
            }

            using (Pen pen = new Pen(Color.Red, 12))
            {
                g.SmoothingMode = System.Drawing.Drawing2D.SmoothingMode.AntiAlias;
                Point[] points = new Point[] { new Point(150, 50), new Point(200, 50), new Point(150, 100), new Point(200, 100) };
                g.DrawCurve(pen, points, 0.5f);
            }
        }
    }
}
