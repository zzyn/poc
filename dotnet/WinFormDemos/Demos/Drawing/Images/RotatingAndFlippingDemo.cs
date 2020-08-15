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
    public partial class RotatingAndFlippingDemo : Form
    {
        public RotatingAndFlippingDemo()
        {
            InitializeComponent();
            this.SetStyle(ControlStyles.ResizeRedraw, true);
        }

        private void RotatingAndFlippingDemo_Paint(object sender, PaintEventArgs e)
        {
            Graphics g = e.Graphics;

            using (Bitmap icon = Demos.Properties.Resources.CCAppWiz_win32.ToBitmap())
            {
                
                g.DrawImage(icon, new Point(10, 10));

                icon.RotateFlip(RotateFlipType.Rotate90FlipNone);

                g.DrawImage(icon, new Point(20 + icon.Width, 10));

                icon.RotateFlip(RotateFlipType.Rotate90FlipNone);

                g.DrawImage(icon, new Point(30 + icon.Width * 2, 10));

                icon.RotateFlip(RotateFlipType.Rotate90FlipNone);

                g.DrawImage(icon, new Point(40 + icon.Width * 3, 10));

                icon.RotateFlip(RotateFlipType.Rotate90FlipNone);
                
                icon.RotateFlip(RotateFlipType.RotateNoneFlipX);
                g.DrawImage(icon, new Point(10, 20 +icon.Height));

                icon.RotateFlip(RotateFlipType.Rotate90FlipNone);
                g.DrawImage(icon, new Point(20 + icon.Width, 20 + icon.Height));

                icon.RotateFlip(RotateFlipType.Rotate90FlipNone);
                g.DrawImage(icon, new Point(30 + icon.Width * 2, 20 + icon.Height));

                icon.RotateFlip(RotateFlipType.Rotate90FlipNone);
                g.DrawImage(icon, new Point(40 + icon.Width * 3, 20 + icon.Height));

            }
        }
    }
}
