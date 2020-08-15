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
    public partial class PathGradientBrushDemo : Form
    {
        public PathGradientBrushDemo()
        {
            InitializeComponent();
            this.SetStyle(ControlStyles.ResizeRedraw, true);
        }

        private void PathGradientBrushDemo_Paint(object sender, PaintEventArgs e)
        {
            int nWidth = 100;
            int nHigh = 100;
            int nLeft = this.ClientRectangle.Left;
            int nTop = this.ClientRectangle.Top;
            Graphics g = e.Graphics;

            Point[] quadPoints = new Point[] 
            { 
                new Point(nLeft + nWidth * 0,nTop + nHigh * 0),
                new Point(nLeft + nWidth * 1,nTop + nHigh * 0),
                new Point(nLeft + nWidth * 1,nTop + nHigh * 1),
                new Point(nLeft + nWidth * 0,nTop + nHigh * 1)
            };

            using (PathGradientBrush brush = new PathGradientBrush(quadPoints)) 
            {
                g.FillRectangle(brush, nLeft, nTop, nWidth, nHigh);
            }

            Point[] diamondPoints = new Point[] 
            { 
                new Point(nLeft + nWidth * 3 / 2,nTop + nHigh * 0),
                new Point(nLeft + nWidth * 1,nTop + nHigh * 1 / 2),
                new Point(nLeft + nWidth * 2,nTop + nHigh * 1 / 2),
                new Point(nLeft + nWidth * 3 / 2,nTop + nHigh * 1)
            };
            using (PathGradientBrush brush = new PathGradientBrush(diamondPoints))
            {
                brush.WrapMode = WrapMode.Tile;
                brush.CenterPoint = new Point(nLeft + nWidth * 3 / 2, nTop + nHigh * 1 / 2);
                g.FillRectangle(brush, nLeft + nWidth * 1, nTop, nWidth, nHigh);
            }

            using (GraphicsPath circle = new GraphicsPath()) 
            {
                circle.AddEllipse(nLeft + nWidth * 2, nTop + nHigh * 0, nWidth, nHigh);
                using (PathGradientBrush brush = new PathGradientBrush(circle)) 
                {
                    brush.WrapMode = WrapMode.Tile;
                    brush.SurroundColors = new Color[] { Color.White};
                    brush.CenterColor = Color.Black;

                    g.FillRectangle(brush, nLeft + nWidth * 2, nTop, nWidth, nHigh);

                }

            }

            Point[] triPoints = new Point[]{ 
                new Point(nLeft + nWidth * 7 /2, nTop + nHigh * 0)
                ,new Point(nLeft + nWidth * 3 , nTop + nHigh * 1)
                ,new Point(nLeft + nWidth * 4 , nTop + nHigh * 1)
            };

            using(PathGradientBrush brush = new PathGradientBrush(triPoints))
            {
                brush.SurroundColors = new Color[] { Color.Red,Color.Yellow,Color.Blue };
                brush.CenterColor = Color.White;
                g.FillRectangle(brush, nLeft + nWidth * 3, nTop + nHigh * 0, nWidth, nHigh);
            }



        }
    }
}
