using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Drawing.Imaging;

namespace Demos
{
    public partial class TransparentDemo : Form
    {
        public TransparentDemo()
        {
            InitializeComponent();
            this.SetStyle(ControlStyles.ResizeRedraw, true);
        }

        private void TransparentDemo_Paint(object sender, PaintEventArgs e)
        {
            Graphics g = e.Graphics;

            using (Bitmap icon = Demos.Properties.Resources.CCAppWiz_win32.ToBitmap())
            {
                ColorMap[] colorMap = new ColorMap[1];
                colorMap[0] = new ColorMap();
                colorMap[0].OldColor = Color.Black;
                colorMap[0].NewColor = Color.Transparent;
                ImageAttributes attr = new ImageAttributes();
                attr.SetRemapTable(colorMap);

                Rectangle rect = new Rectangle(0, 0, icon.Width, icon.Height);
                rect.Offset(10, 10);

                g.DrawImage(icon, rect, 0, 0, rect.Width, rect.Height, g.PageUnit, attr);

                g.DrawImage(icon, new Point(20 + icon.Width, 10));
            }
        }
    }
}
