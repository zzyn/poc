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
    public partial class ScalingAndSkewingDemo : Form
    {
        public ScalingAndSkewingDemo()
        {
            InitializeComponent();
            this.SetStyle(ControlStyles.ResizeRedraw, true);
        }

        private void ScalingAndClippingDemo_Paint(object sender, PaintEventArgs e)
        {
            Graphics g = e.Graphics;

            using (Bitmap icon = Demos.Properties.Resources.CCAppWiz_win32.ToBitmap())
            {
                g.DrawImage(icon, new Point(10, 10));

                //Scaling
                g.DrawImage(icon, new Rectangle(icon.Width + 20, 10,icon.Width/2, icon.Height/2));

                //Skewing
                Size offset =new Size(15,15);
                Rectangle rect = new Rectangle(10,icon.Height + 20,icon.Width,icon.Height);
                
                Point[] points = new Point[3];
                points[0] = new Point(rect.Left + offset.Width, rect.Top + offset.Height);
                points[1] = new Point(rect.Right, rect.Top + offset.Height);
                points[2] = new Point(rect.Left, rect.Bottom - offset.Height);

                g.DrawImage(icon, points);
                

            }
        }
    }
}
