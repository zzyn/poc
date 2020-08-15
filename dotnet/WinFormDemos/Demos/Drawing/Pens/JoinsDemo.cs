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
    public partial class JoinsDemo : Form
    {
        public JoinsDemo()
        {
            InitializeComponent();
            this.SetStyle(ControlStyles.ResizeRedraw, true);
        }

        private void JoinsDemo_Paint(object sender, PaintEventArgs e)
        {
            Graphics g = e.Graphics;

            using (Pen p = new Pen(Color.Red, 12)) 
            {
                p.LineJoin = LineJoin.MiterClipped;
                //p.MiterLimit = 5.0F;
                g.DrawRectangle(p, new Rectangle(50,50,50,50));

                p.LineJoin = LineJoin.Miter;
                //p.MiterLimit = 5.0F;
                g.DrawRectangle(p, new Rectangle(150, 50, 50, 50));

                p.LineJoin = LineJoin.Bevel;
                //p.MiterLimit = 5.0F;
                g.DrawRectangle(p, new Rectangle(50, 150, 50, 50));

                p.LineJoin = LineJoin.Round;
                //p.MiterLimit = 5.0F;
                g.DrawRectangle(p, new Rectangle(150, 150, 50, 50));
                
            }
        }
    }
}
