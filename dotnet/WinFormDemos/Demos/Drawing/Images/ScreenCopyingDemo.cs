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
    public partial class ScreenCopyingDemo : Form
    {
        public ScreenCopyingDemo()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Rectangle screenRect = Screen.PrimaryScreen.WorkingArea;
            Bitmap dumpBitmap = new Bitmap(screenRect.Width, screenRect.Height);
            using (Graphics g = Graphics.FromImage(dumpBitmap)) 
            {
                g.CopyFromScreen(0, 0, 0, 0, new Size(dumpBitmap.Width, dumpBitmap.Height));

                this.CreateGraphics().DrawImage(dumpBitmap, this.ClientRectangle);
            }
        }
    }
}
