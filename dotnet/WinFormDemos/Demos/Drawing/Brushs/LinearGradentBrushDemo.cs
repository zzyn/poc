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
    public partial class LinearGradentBrushDemo : Form
    {
        public LinearGradentBrushDemo()
        {
            InitializeComponent();
            this.SetStyle(ControlStyles.ResizeRedraw, true);
        }

        private void LinearGradentBrushDemo_Paint(object sender, PaintEventArgs e)
        {
            Graphics g = e.Graphics;
            int nLeft = this.ClientRectangle.Left;
            int nTop = this.ClientRectangle.Top;
            int nWidth = 200;
            int nHeight = 30;

            using (LinearGradientBrush brush = new LinearGradientBrush(
                this.ClientRectangle,Color.White,Color.Black,  LinearGradientMode.Horizontal)) 
            {
                g.FillRectangle(brush, nLeft, nTop, nWidth, nHeight);
                g.DrawString("Normal", this.Font, new SolidBrush(Color.Black), nLeft, nTop);
                
                nTop += nHeight;
                brush.SetBlendTriangularShape(0.5f);
                g.FillRectangle(brush, nLeft, nTop, nWidth, nHeight);
                g.DrawString("Triangle", this.Font, new SolidBrush(Color.Black), nLeft, nTop);

                nTop += nHeight;
                brush.SetSigmaBellShape(0.5f);
                g.FillRectangle(brush, nLeft, nTop, nWidth, nHeight);
                g.DrawString("Bell", this.Font, new SolidBrush(Color.Black), nLeft, nTop);


                nTop += nHeight;
                //混色器
                ColorBlend blend = new ColorBlend();
                blend.Colors = new Color[] { Color.White, Color.Red, Color.Black };
                blend.Positions = new float[] { 0.0f, 0.5f, 1.0f };

                brush.InterpolationColors = blend;

                g.FillRectangle(brush, nLeft, nTop, nWidth, nHeight);
                g.DrawString("Custom Colors", this.Font, new SolidBrush(Color.Black), nLeft, nTop);


            }

           

        }
    }
}
