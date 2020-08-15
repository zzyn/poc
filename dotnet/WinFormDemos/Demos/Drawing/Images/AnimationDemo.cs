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
    public partial class AnimationDemo : Form
    {
        public AnimationDemo()
        {
            InitializeComponent();
            this.SetStyle(ControlStyles.ResizeRedraw, true);
        }

        private void AnimationDemo_Paint(object sender, PaintEventArgs e)
        {
            Bitmap gif = Demos.Properties.Resources.Animation;
            int timeFrames = gif.GetFrameCount(FrameDimension.Time);
            //int pageFrames = gif.GetFrameCount(FrameDimension.Page);
            //int resolutionFrames = gif.GetFrameCount(FrameDimension.Resolution);
            int frame = new Random().Next(0, gif.FrameDimensionsList.Length - 1);
            gif.SelectActiveFrame(FrameDimension.Time, frame);
            e.Graphics.DrawImage(gif, this.ClientRectangle);
        }
    }
}
