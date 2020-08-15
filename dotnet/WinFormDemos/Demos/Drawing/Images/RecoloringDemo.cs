using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Drawing.Drawing2D;
using System.Drawing.Imaging;

namespace Demos
{
    public partial class RecoloringDemo : Form
    {
        public RecoloringDemo()
        {
            InitializeComponent();
            this.SetStyle(ControlStyles.ResizeRedraw, true);
        }

        private void RecoloringDemo_Paint(object sender, PaintEventArgs e)
        {
             Graphics g = e.Graphics;

             using (Bitmap icon = Demos.Properties.Resources.Disable)
             {
                 ColorMap[] colorMap = new ColorMap[1];
                 colorMap[0] = new ColorMap();
                 colorMap[0].OldColor = Color.White;
                 colorMap[0].NewColor = Color.Transparent;
                 
                 ImageAttributes attr = new ImageAttributes();
                 attr.SetRemapTable(colorMap);

                 

                 Rectangle rect = new Rectangle(10,10, icon.Width, icon.Height);
                 
                 Bitmap test = Demos.Properties.Resources.TestCase.ToBitmap();

                 g.DrawImage(test, new Rectangle(10, 10, test.Width, test.Height));
                
                 g.DrawImage(icon, rect, 0, 0, rect.Width, rect.Height, g.PageUnit, attr);

                 g.DrawImage(icon, new Point(20 + icon.Width,10));

                 Image tt = icon;

                 

                

                 icon.Save("C:\\Test.png");
             }

             
        }
    }
}
