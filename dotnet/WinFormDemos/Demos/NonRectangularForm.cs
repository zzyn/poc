using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Drawing.Drawing2D;

namespace Demo
{
    public partial class NonRectangularForm : Form
    {
        public NonRectangularForm()
        {
            InitializeComponent();
        }

        private void OnLoad(object sender, EventArgs e)
        {
            SetEllipseRegion(); 
        }

        private void OnSizeChanged(object sender, EventArgs e)
        {
            SetEllipseRegion();
        }

        private void SetEllipseRegion() 
        {
            this.FormBorderStyle = FormBorderStyle.None;
            Rectangle rect = this.ClientRectangle;

            using (GraphicsPath path = new GraphicsPath()) 
            {
                path.AddEllipse(rect);
                this.Region = new Region(path);
            }
        }

        private void btnClose_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        Point downPoint = Point.Empty;

        private void OnMouseDown(object sender, MouseEventArgs e)
        {
            if (e.Button != MouseButtons.Left) return;
            downPoint = new Point(e.X, e.Y);
        }

        private void OnMouseUp(object sender, MouseEventArgs e)
        {
            if (e.Button != MouseButtons.Left) return;
            downPoint = Point.Empty;
           
        }

        private void OnMouseMove(object sender, MouseEventArgs e)
        {
            if (downPoint == Point.Empty) return;
            Point loc = new Point(this.Left + e.X - downPoint.X, this.Top + e.Y - downPoint.Y);
            this.Location = loc;
        }

    }
}
