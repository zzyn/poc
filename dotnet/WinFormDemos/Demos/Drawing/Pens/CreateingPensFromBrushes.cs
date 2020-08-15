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
    public partial class CreateingPensFromBrushes : Form
    {
        public CreateingPensFromBrushes()
        {
            InitializeComponent();
            this.SetStyle(ControlStyles.ResizeRedraw, true);
        }

        private void CreateingPensFromBrushes_Paint(object sender, PaintEventArgs e)
        {
            using (LinearGradientBrush brush = new LinearGradientBrush(this.ClientRectangle, Color.Empty, Color.Empty, 45)) 
            {
                ColorBlend blend = new ColorBlend();
                blend.Colors = new Color[] { Color.Red, Color.Green, Color.Blue };
                blend.Positions = new float[] { 0, 0.5f, 1 };
                brush.InterpolationColors = blend;
                using (Pen pen = new Pen(brush,12)) 
                {
                    e.Graphics.DrawEllipse(pen, new Rectangle(0, 0, 200, 200));
                }
            }
        }
    }
}
