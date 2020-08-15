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
    public partial class FillModesDemo : Form
    {
        public FillModesDemo()
        {
            InitializeComponent();
            this.SetStyle(ControlStyles.ResizeRedraw, true);
        }

        private void FillModesDemo_Paint(object sender, PaintEventArgs e)
        {
            using (GraphicsPath path = new GraphicsPath()) 
            {
                path.FillMode = FillMode.Alternate;

                path.AddEllipse(new Rectangle(0, 0, 100, 50));
                path.StartFigure();
                path.AddEllipse(new Rectangle(25, 25, 100, 50));

                e.Graphics.FillPath(Brushes.Yellow, path);
                e.Graphics.DrawPath(Pens.Black, path);
            }



            using (GraphicsPath path = new GraphicsPath()) 
            {
                path.FillMode = FillMode.Winding;

                path.AddEllipse(new Rectangle(200, 0, 100, 50));
                path.StartFigure();
                path.AddEllipse(new Rectangle(225,25, 100, 50));

                e.Graphics.FillPath(Brushes.Green, path);
                e.Graphics.DrawPath(Pens.Black, path);
            }
            
        }
    }
}
