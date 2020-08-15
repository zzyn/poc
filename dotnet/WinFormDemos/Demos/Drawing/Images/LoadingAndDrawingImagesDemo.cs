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
    public partial class LoadingAndDrawingImagesDemo : Form
    {
        public LoadingAndDrawingImagesDemo()
        {
            InitializeComponent();
            this.SetStyle(ControlStyles.ResizeRedraw, true);
        }

        private void LoadingAndDrawingImagesDemo_Paint(object sender, PaintEventArgs e)
        {
            

            using(Bitmap icon = Demos.Properties.Resources.CCAppWiz_win32.ToBitmap())
            {
                e.Graphics.DrawImage(icon, new Point(50, 50));
               
            }
            
        }
    }
}
