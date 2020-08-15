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
    public partial class SavingAndRestoringGraphicsSettingsDemo : Form
    {
        public SavingAndRestoringGraphicsSettingsDemo()
        {
            InitializeComponent();
            this.SetStyle(ControlStyles.ResizeRedraw, true);
        }

        private void SavingAndRestoringGraphicsSettingsDemo_Paint(object sender, PaintEventArgs e)
        {
            Graphics g = e.Graphics;

            GraphicsState oldState = g.Save();

            g.SmoothingMode = SmoothingMode.AntiAlias;
            g.FillEllipse(new SolidBrush(Color.Red), new Rectangle(50, 50, 100, 50));

            g.Restore(oldState);

            g.FillEllipse(new SolidBrush(Color.Red), new Rectangle(50, 200, 100, 50));
        }
    }
}
