using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Demos;

namespace Demo
{
    public partial class MainForm : Form
    {
        public MainForm()
        {
            InitializeComponent();
            this.SetStyle(ControlStyles.ResizeRedraw, true);
        }


        private void tsmiNonForm_Click(object sender, EventArgs e)
        {
            NonRectangularForm test = new NonRectangularForm();
            Random random = new Random();

            test.BackColor = Color.FromArgb(random.Next(0, 255), random.Next(0, 255), random.Next(0, 255));
            test.MdiParent = this;
            test.Show();
        }

        private void MainForm_Resize(object sender, EventArgs e)
        {
            switch (this.WindowState) 
            {
                case FormWindowState.Minimized:
                    this.Hide();
                    this.ctrNotifyIcon.Visible = true;
                    break;
                default:
                   
                    this.Show();
                    this.ctrNotifyIcon.Visible = false;
                    break;
            }
        }

        private void ctrlNotifyIcon_MouseDoubleClick(object sender, MouseEventArgs e)
        {
            this.Show();
            this.Activate();
        }

        private void tsmiNotification_Click(object sender, EventArgs e)
        {
            this.ctrNotifyIcon.ShowBalloonTip(10);
        }



        private void tsmiNewWindow_Click(object sender, EventArgs e)
        {
            Form test = new Form();
            test.Text = "TTT";
            test.MdiParent = this;
            test.Show();
        }

        private void tsmiArrangeIcon_Click(object sender, EventArgs e)
        {
            this.LayoutMdi(MdiLayout.ArrangeIcons);
        }

        private void tsmiCascade_Click(object sender, EventArgs e)
        {
            this.LayoutMdi(MdiLayout.Cascade);
        }

        private void tsmiTileHorizontally_Click(object sender, EventArgs e)
        {
            this.LayoutMdi(MdiLayout.TileHorizontal);
        }

        private void tsmiTileVertically_Click(object sender, EventArgs e)
        {
            this.LayoutMdi(MdiLayout.TileVertical);
        }

        private void tsmiCloseAllWindows_Click(object sender, EventArgs e)
        {
            foreach (Form frm in this.MdiChildren)
            {
                frm.Close();
            }

        }


        private void tsmiColorDialog_Click(object sender, EventArgs e)
        {
            ColorDialog cd = new ColorDialog();
            cd.Color = Color.Red;
            if (DialogResult.OK == cd.ShowDialog()) 
            {
                MessageBox.Show(cd.Color.ToString());
            }
        }

        private void tsmiModelessDialog_Click(object sender, EventArgs e)
        {
            ModelessFormDialog mld = new ModelessFormDialog();
            mld.Accept += Modeless_Accept;
            mld.Show();
        }

        void Modeless_Accept(object sender, EventArgs e)
        {
            ModelessFormDialog mld = sender as ModelessFormDialog;
            MessageBox.Show(mld.Text);
            mld.Close();
            
        }

        private void tsmiFormatNotificationDialog_Click(object sender, EventArgs e)
        {
            FormatNotificationFormDialog fnfd = new FormatNotificationFormDialog();
            fnfd.Accept += Modeless_Accept;
            fnfd.Show();
        }

        
        private void tsmiDrawingToTheScreen_Click(object sender, EventArgs e)
        {
            DrawingToScreen frm = new DrawingToScreen();
            frm.MdiParent = this;
            frm.Show();
        }

        private void tsmiPaintEvent_Click(object sender, EventArgs e)
        {
            HandlingThePaintEvent frm = new HandlingThePaintEvent();
            frm.MdiParent = this;
            frm.Show();
        }

        private void tsmiBrushDemos_Click(object sender, EventArgs e)
        {
            BrushsDemo frm = new BrushsDemo();
            frm.MdiParent = this;
            frm.Show();
        }

        private void tsmiTextureBrushs_Click(object sender, EventArgs e)
        {
            TextureBrushes frm = new TextureBrushes();
            frm.MdiParent = this;
            frm.Show();
        }

        private void tsmiHatchBrushs_Click(object sender, EventArgs e)
        {
            HatchBrushes frm = new HatchBrushes();
            frm.MdiParent = this;
            frm.Show();
        }

        private void tsmiLinearGradientBrushs_Click(object sender, EventArgs e)
        {
            LinearGradentBrushDemo frm = new LinearGradentBrushDemo();
            frm.MdiParent = this;
            frm.Show();
        }

        private void tsmiPathGradientBrushs_Click(object sender, EventArgs e)
        {
            PathGradientBrushDemo frm = new PathGradientBrushDemo();
            frm.MdiParent = this;
            frm.Show();
        }

        private void tsmiLineCaps_Click(object sender, EventArgs e)
        {
            LineCapsDemo frm = new LineCapsDemo();
            frm.MdiParent = this;
            frm.Show();
        }

        private void tsmiDashes_Click(object sender, EventArgs e)
        {
            DashesDemo frm = new DashesDemo();
            frm.MdiParent = this;
            frm.Show();
        }

        private void tsmiPenAlignment_Click(object sender, EventArgs e)
        {
            PenAlignmentDemo frm = new PenAlignmentDemo();
            frm.MdiParent = this;
            frm.Show();
        }

        private void tsmiJoins_Click(object sender, EventArgs e)
        {
            JoinsDemo frm = new JoinsDemo();
            frm.MdiParent = this;
            frm.Show();
        }

        private void tsmiCreatingPensFromBrushes_Click(object sender, EventArgs e)
        {
            CreateingPensFromBrushes frm = new CreateingPensFromBrushes();
            frm.MdiParent = this;
            frm.Show();
        }

        private void tsmiCurves_Click(object sender, EventArgs e)
        {
            CurvesDemo frm = new CurvesDemo();
            frm.MdiParent = this;
            frm.Show();
        }

        private void tsmiSmoothingModes_Click(object sender, EventArgs e)
        {
            SmoothingModesDemo frm = new SmoothingModesDemo();
            frm.MdiParent = this;
            frm.Show();
        }

        private void tsmiSavingAndRestoringGraphicsSettings_Click(object sender, EventArgs e)
        {
            SavingAndRestoringGraphicsSettingsDemo frm = new SavingAndRestoringGraphicsSettingsDemo();
            frm.MdiParent = this;
            frm.Show();
        }

        private void tsmiPathDemos_Click(object sender, EventArgs e)
        {
            PathsDemo frm = new PathsDemo();
            frm.MdiParent = this;
            frm.Show();
        }

        private void tsmiFillModes_Click(object sender, EventArgs e)
        {
            FillModesDemo frm = new FillModesDemo();
            frm.MdiParent = this;
            frm.Show();
        }

        private void tsmiLoadAndDrawingImges_Click(object sender, EventArgs e)
        {
            LoadingAndDrawingImagesDemo frm = new LoadingAndDrawingImagesDemo();
            frm.MdiParent = this;
            frm.Show();
        }

        private void tsmiScalingAndSkewing_Click(object sender, EventArgs e)
        {
            ScalingAndSkewingDemo frm = new ScalingAndSkewingDemo();
            frm.MdiParent = this;
            frm.Show();
        }

        private void tsmiRotatingAndFlipping_Click(object sender, EventArgs e)
        {
            RotatingAndFlippingDemo frm = new RotatingAndFlippingDemo();
            frm.MdiParent = this;
            frm.Show();
        }

        private void tsmiRecoloring_Click(object sender, EventArgs e)
        {
            RecoloringDemo frm = new RecoloringDemo();
            frm.MdiParent = this;
            frm.Show();
        }

        private void tsmiTransparency_Click(object sender, EventArgs e)
        {
            TransparentDemo frm = new TransparentDemo();
            frm.MdiParent = this;
            frm.Show();
        }

        private void tsmiAnimation_Click(object sender, EventArgs e)
        {
            AnimationDemo frm = new AnimationDemo();
            frm.MdiParent = this;
            frm.Show();
        }

        private void tsmiDrawingToImages_Click(object sender, EventArgs e)
        {
            DrawingToImageDemo frm = new DrawingToImageDemo();
            frm.MdiParent = this;
            frm.Show();
        }

        private void tsmiScreenCopying_Click(object sender, EventArgs e)
        {
            ScreenCopyingDemo frm = new ScreenCopyingDemo();
            frm.MdiParent = this;
            frm.Show();
        }

        private void tsmiIcons_Click(object sender, EventArgs e)
        {

        }

        private void tsmiCursors_Click(object sender, EventArgs e)
        {

        }

        

       

       
    }
}
