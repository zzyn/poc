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
    public partial class LineCapsDemo : Form
    {
        public LineCapsDemo()
        {
            InitializeComponent();
            this.SetStyle(ControlStyles.ResizeRedraw, true);
        }

        private void LineCapsDemo_Paint(object sender, PaintEventArgs e)
        {
            int nLeft = this.ClientRectangle.Left;
            int nTop =  this.ClientRectangle.Top;
            int nWidth = 100;
            int nHigh = 30;
            Graphics g = e.Graphics;
            using(Pen p = new Pen(Color.Red,nHigh/2))
            {
                g.DrawString("AnchorMask", this.Font, new SolidBrush(Color.Black), nLeft + nWidth * 0, nTop + nHigh * 0);
                p.EndCap = LineCap.AnchorMask;
                g.DrawLine(p, nLeft + nWidth * 0, nTop + nHigh * 1, nLeft + nWidth * 1, nTop + nHigh * 1);

                g.DrawString("ArrowAnchor", this.Font, new SolidBrush(Color.Black), nLeft + nWidth * 0, nTop + nHigh * 2);
                p.EndCap = LineCap.ArrowAnchor;
                g.DrawLine(p, nLeft + nWidth * 0, nTop + nHigh * 3, nLeft + nWidth * 1, nTop + nHigh * 3);

                g.DrawString("Custom", this.Font, new SolidBrush(Color.Black), nLeft + nWidth * 0, nTop + nHigh * 4);
                p.EndCap = LineCap.Custom;
                p.CustomEndCap = new AdjustableArrowCap(3f, 3f, false);
                g.DrawLine(p, nLeft + nWidth * 0, nTop + nHigh * 5, nLeft + nWidth * 1, nTop + nHigh * 5);
                

               
            }
           

        }
    }
}
