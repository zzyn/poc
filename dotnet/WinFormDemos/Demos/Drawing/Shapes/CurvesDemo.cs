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
    public partial class CurvesDemo : Form
    {
        public CurvesDemo()
        {
            InitializeComponent();
            this.SetStyle(ControlStyles.ResizeRedraw, true);
        }

        private void CurvesDemo_Paint(object sender, PaintEventArgs e)
        {
            Graphics g = e.Graphics;
            
           
            using(Pen pen = new Pen(Color.Red,12))
            {
                Point[] points = new Point[] { new Point(50,50),new Point(100,50),new Point(50,100),new Point(100,100)};
                g.DrawCurve(pen, points,0);
            }

            using (Pen pen = new Pen(Color.Red, 12))
            {
                Point[] points = new Point[] { new Point(150, 50), new Point(200, 50), new Point(150, 100), new Point(200, 100) };
                g.DrawCurve(pen, points,0.5f);
            }

            using (Pen pen = new Pen(Color.Red, 12))
            {
                Point[] points = new Point[] { new Point(250, 50), new Point(300, 50), new Point(250, 100), new Point(300, 100) };
                g.DrawCurve(pen, points, 1);
            }

            using (Pen pen = new Pen(Color.Red, 12))
            {
                g.DrawBezier(pen, new Point(50, 200) , new Point(100, 200), new Point(50, 150), new Point(100, 150));
            }

            using (Pen pen = new Pen(Color.Red, 12))
            {
                g.DrawBezier(pen, new Point(150, 150), new Point(200, 150), new Point(200, 200), new Point(150, 200));
            }

            using (Pen pen = new Pen(Color.Red, 12))
            {
                g.DrawBezier(pen, new Point(250, 150), new Point(300, 150), new Point(250, 200), new Point(300, 200));
            }
            
        }
    }
}
