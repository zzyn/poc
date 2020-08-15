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
    public partial class PathsDemo : Form
    {
        public PathsDemo()
        {
            InitializeComponent();
            this.SetStyle(ControlStyles.ResizeRedraw, true);
        }

        private void PathsDemo_Paint(object sender, PaintEventArgs e)
        {
            Graphics g = e.Graphics;
            int nWidth = this.ClientRectangle.Width;
            int nHeight = this.ClientRectangle.Height;
            Rectangle rect = new Rectangle(10, 10, nWidth - 20, nHeight - 20);
            using (GraphicsPath path = GetRoundedRectPath(rect, nWidth / 10)) 
            {
                g.FillPath(Brushes.Yellow, path);
                g.DrawPath(Pens.Black, path);
            }
        }

        private GraphicsPath GetRoundedRectPath(Rectangle rect, int radius)
        {
            int diameter = 2 * radius;

            Rectangle arcRect = new Rectangle(rect.Location, new Size(diameter, diameter));

            GraphicsPath path = new GraphicsPath();
            path.AddArc(arcRect, 180, 90);

            arcRect.X = rect.Right - diameter;
            path.AddArc(arcRect, 270, 90);

            arcRect.Y = rect.Bottom - diameter;
            path.AddArc(arcRect, 0, 90);

            arcRect.X = rect.Left;
            path.AddArc(arcRect, 90, 90);
            
            path.CloseFigure();

            return path;
        }
    }
}
