
namespace BigFrame
{
    partial class DefaultForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(DefaultForm));
            this.VersionCaption = new Common.BaseControl.CaptionPanel();
            this.pnlVersion = new System.Windows.Forms.Panel();
            this.flpVersionInfo = new System.Windows.Forms.FlowLayoutPanel();
            this.label2 = new System.Windows.Forms.Label();
            this.flpVersionFace = new System.Windows.Forms.FlowLayoutPanel();
            this.picVersion = new System.Windows.Forms.PictureBox();
            this.CopyrightCaption = new Common.BaseControl.CaptionPanel();
            this.pnlCopyright = new System.Windows.Forms.Panel();
            this.flpCopyrightInfo = new System.Windows.Forms.FlowLayoutPanel();
            this.flpCopyrightFace = new System.Windows.Forms.FlowLayoutPanel();
            this.qriCopyright = new Gma.QrCodeNet.Encoding.Windows.Forms.QrCodeImgControl();
            this.AuthorCaption = new Common.BaseControl.CaptionPanel();
            this.pnlAuthor = new System.Windows.Forms.Panel();
            this.flpAuthorInfo = new System.Windows.Forms.FlowLayoutPanel();
            this.lblNameCaption = new System.Windows.Forms.Label();
            this.lblName = new System.Windows.Forms.Label();
            this.flpAuthorFace = new System.Windows.Forms.FlowLayoutPanel();
            this.picAuthor = new System.Windows.Forms.PictureBox();
            this.label1 = new System.Windows.Forms.Label();
            this.qrCodeImgControl1 = new Gma.QrCodeNet.Encoding.Windows.Forms.QrCodeImgControl();
            this.lblCopyrightInfo = new System.Windows.Forms.Label();
            this.VersionCaption.SuspendLayout();
            this.pnlVersion.SuspendLayout();
            this.flpVersionInfo.SuspendLayout();
            this.flpVersionFace.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picVersion)).BeginInit();
            this.CopyrightCaption.SuspendLayout();
            this.pnlCopyright.SuspendLayout();
            this.flpCopyrightInfo.SuspendLayout();
            this.flpCopyrightFace.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.qriCopyright)).BeginInit();
            this.AuthorCaption.SuspendLayout();
            this.pnlAuthor.SuspendLayout();
            this.flpAuthorInfo.SuspendLayout();
            this.flpAuthorFace.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picAuthor)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.qrCodeImgControl1)).BeginInit();
            this.SuspendLayout();
            // 
            // VersionCaption
            // 
            this.VersionCaption.ButtonLabelBackColor = System.Drawing.Color.DeepSkyBlue;
            this.VersionCaption.ButtonLabelFocusBackColor = System.Drawing.Color.BlueViolet;
            this.VersionCaption.ButtonLabelForeColor = System.Drawing.Color.White;
            this.VersionCaption.Controls.Add(this.pnlVersion);
            this.VersionCaption.Editable = false;
            this.VersionCaption.EnphasisColor = System.Drawing.Color.Yellow;
            this.VersionCaption.ErrorBackColor = System.Drawing.Color.SeaShell;
            this.VersionCaption.GotFocusBackColor = System.Drawing.Color.LemonChiffon;
            this.VersionCaption.GotFocusLabelForeColor = System.Drawing.Color.Brown;
            this.VersionCaption.LabelFont = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.VersionCaption.LabelForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(102)))), ((int)(((byte)(0)))));
            this.VersionCaption.Location = new System.Drawing.Point(0, 139);
            this.VersionCaption.Name = "VersionCaption";
            this.VersionCaption.Size = new System.Drawing.Size(800, 139);
            this.VersionCaption.TabIndex = 0;
            this.VersionCaption.Text = "Version";
            this.VersionCaption.WaitCounter = 0;
            this.VersionCaption.Waiting = false;
            this.VersionCaption.WarningBackColor = System.Drawing.Color.LightYellow;
            // 
            // pnlVersion
            // 
            this.pnlVersion.Controls.Add(this.flpVersionInfo);
            this.pnlVersion.Controls.Add(this.flpVersionFace);
            this.pnlVersion.Dock = System.Windows.Forms.DockStyle.Top;
            this.pnlVersion.Location = new System.Drawing.Point(15, 24);
            this.pnlVersion.Name = "pnlVersion";
            this.pnlVersion.Size = new System.Drawing.Size(774, 104);
            this.pnlVersion.TabIndex = 0;
            // 
            // flpVersionInfo
            // 
            this.flpVersionInfo.Controls.Add(this.label2);
            this.flpVersionInfo.Dock = System.Windows.Forms.DockStyle.Fill;
            this.flpVersionInfo.Location = new System.Drawing.Point(102, 0);
            this.flpVersionInfo.Name = "flpVersionInfo";
            this.flpVersionInfo.Size = new System.Drawing.Size(672, 104);
            this.flpVersionInfo.TabIndex = 1;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(10, 10);
            this.label2.Margin = new System.Windows.Forms.Padding(10);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(124, 13);
            this.label2.TabIndex = 1;
            this.label2.Text = "Version Number : 1.0.0.0";
            // 
            // flpVersionFace
            // 
            this.flpVersionFace.Controls.Add(this.picVersion);
            this.flpVersionFace.Dock = System.Windows.Forms.DockStyle.Left;
            this.flpVersionFace.Location = new System.Drawing.Point(0, 0);
            this.flpVersionFace.Name = "flpVersionFace";
            this.flpVersionFace.Size = new System.Drawing.Size(102, 104);
            this.flpVersionFace.TabIndex = 2;
            // 
            // picVersion
            // 
            this.picVersion.Dock = System.Windows.Forms.DockStyle.Top;
            this.picVersion.Location = new System.Drawing.Point(2, 2);
            this.picVersion.Margin = new System.Windows.Forms.Padding(2);
            this.picVersion.Name = "picVersion";
            this.picVersion.Size = new System.Drawing.Size(100, 100);
            this.picVersion.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picVersion.TabIndex = 1;
            this.picVersion.TabStop = false;
            // 
            // CopyrightCaption
            // 
            this.CopyrightCaption.ButtonLabelBackColor = System.Drawing.Color.DeepSkyBlue;
            this.CopyrightCaption.ButtonLabelFocusBackColor = System.Drawing.Color.BlueViolet;
            this.CopyrightCaption.ButtonLabelForeColor = System.Drawing.Color.White;
            this.CopyrightCaption.Controls.Add(this.pnlCopyright);
            this.CopyrightCaption.Editable = false;
            this.CopyrightCaption.EnphasisColor = System.Drawing.Color.Yellow;
            this.CopyrightCaption.ErrorBackColor = System.Drawing.Color.SeaShell;
            this.CopyrightCaption.GotFocusBackColor = System.Drawing.Color.LemonChiffon;
            this.CopyrightCaption.GotFocusLabelForeColor = System.Drawing.Color.Brown;
            this.CopyrightCaption.LabelFont = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.CopyrightCaption.LabelForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(102)))), ((int)(((byte)(0)))));
            this.CopyrightCaption.Location = new System.Drawing.Point(0, 278);
            this.CopyrightCaption.Name = "CopyrightCaption";
            this.CopyrightCaption.Size = new System.Drawing.Size(800, 139);
            this.CopyrightCaption.TabIndex = 0;
            this.CopyrightCaption.Text = "Copyright";
            this.CopyrightCaption.WaitCounter = 0;
            this.CopyrightCaption.Waiting = false;
            this.CopyrightCaption.WarningBackColor = System.Drawing.Color.LightYellow;
            // 
            // pnlCopyright
            // 
            this.pnlCopyright.Controls.Add(this.flpCopyrightInfo);
            this.pnlCopyright.Controls.Add(this.flpCopyrightFace);
            this.pnlCopyright.Dock = System.Windows.Forms.DockStyle.Top;
            this.pnlCopyright.Location = new System.Drawing.Point(15, 24);
            this.pnlCopyright.Name = "pnlCopyright";
            this.pnlCopyright.Size = new System.Drawing.Size(774, 104);
            this.pnlCopyright.TabIndex = 0;
            // 
            // flpCopyrightInfo
            // 
            this.flpCopyrightInfo.Controls.Add(this.lblCopyrightInfo);
            this.flpCopyrightInfo.Dock = System.Windows.Forms.DockStyle.Fill;
            this.flpCopyrightInfo.Location = new System.Drawing.Point(102, 0);
            this.flpCopyrightInfo.Name = "flpCopyrightInfo";
            this.flpCopyrightInfo.Size = new System.Drawing.Size(672, 104);
            this.flpCopyrightInfo.TabIndex = 1;
            // 
            // flpCopyrightFace
            // 
            this.flpCopyrightFace.Controls.Add(this.qriCopyright);
            this.flpCopyrightFace.Dock = System.Windows.Forms.DockStyle.Left;
            this.flpCopyrightFace.Location = new System.Drawing.Point(0, 0);
            this.flpCopyrightFace.Name = "flpCopyrightFace";
            this.flpCopyrightFace.Size = new System.Drawing.Size(102, 104);
            this.flpCopyrightFace.TabIndex = 4;
            // 
            // qriCopyright
            // 
            this.qriCopyright.ErrorCorrectLevel = Gma.QrCodeNet.Encoding.ErrorCorrectionLevel.Q;
            this.qriCopyright.Image = ((System.Drawing.Image)(resources.GetObject("qriCopyright.Image")));
            this.qriCopyright.Location = new System.Drawing.Point(2, 2);
            this.qriCopyright.Margin = new System.Windows.Forms.Padding(2);
            this.qriCopyright.Name = "qriCopyright";
            this.qriCopyright.QuietZoneModule = Gma.QrCodeNet.Encoding.Windows.Render.QuietZoneModules.Two;
            this.qriCopyright.Size = new System.Drawing.Size(100, 100);
            this.qriCopyright.SizeMode = System.Windows.Forms.PictureBoxSizeMode.CenterImage;
            this.qriCopyright.TabIndex = 0;
            this.qriCopyright.TabStop = false;
            this.qriCopyright.Text = "Made in China";
            // 
            // AuthorCaption
            // 
            this.AuthorCaption.ButtonLabelBackColor = System.Drawing.Color.DeepSkyBlue;
            this.AuthorCaption.ButtonLabelFocusBackColor = System.Drawing.Color.BlueViolet;
            this.AuthorCaption.ButtonLabelForeColor = System.Drawing.Color.White;
            this.AuthorCaption.Controls.Add(this.pnlAuthor);
            this.AuthorCaption.Editable = false;
            this.AuthorCaption.EnphasisColor = System.Drawing.Color.Yellow;
            this.AuthorCaption.ErrorBackColor = System.Drawing.Color.SeaShell;
            this.AuthorCaption.GotFocusBackColor = System.Drawing.Color.LemonChiffon;
            this.AuthorCaption.GotFocusLabelForeColor = System.Drawing.Color.Brown;
            this.AuthorCaption.LabelFont = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.AuthorCaption.LabelForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(102)))), ((int)(((byte)(0)))));
            this.AuthorCaption.Location = new System.Drawing.Point(0, 0);
            this.AuthorCaption.Name = "AuthorCaption";
            this.AuthorCaption.Size = new System.Drawing.Size(800, 139);
            this.AuthorCaption.TabIndex = 1;
            this.AuthorCaption.Text = "Author";
            this.AuthorCaption.WaitCounter = 0;
            this.AuthorCaption.Waiting = false;
            this.AuthorCaption.WarningBackColor = System.Drawing.Color.LightYellow;
            // 
            // pnlAuthor
            // 
            this.pnlAuthor.Controls.Add(this.flpAuthorInfo);
            this.pnlAuthor.Controls.Add(this.flpAuthorFace);
            this.pnlAuthor.Dock = System.Windows.Forms.DockStyle.Top;
            this.pnlAuthor.Location = new System.Drawing.Point(15, 24);
            this.pnlAuthor.Name = "pnlAuthor";
            this.pnlAuthor.Size = new System.Drawing.Size(774, 104);
            this.pnlAuthor.TabIndex = 0;
            // 
            // flpAuthorInfo
            // 
            this.flpAuthorInfo.Controls.Add(this.lblNameCaption);
            this.flpAuthorInfo.Controls.Add(this.lblName);
            this.flpAuthorInfo.Dock = System.Windows.Forms.DockStyle.Fill;
            this.flpAuthorInfo.Location = new System.Drawing.Point(102, 0);
            this.flpAuthorInfo.Name = "flpAuthorInfo";
            this.flpAuthorInfo.Size = new System.Drawing.Size(672, 104);
            this.flpAuthorInfo.TabIndex = 1;
            // 
            // lblNameCaption
            // 
            this.lblNameCaption.AutoSize = true;
            this.lblNameCaption.Location = new System.Drawing.Point(10, 10);
            this.lblNameCaption.Margin = new System.Windows.Forms.Padding(10);
            this.lblNameCaption.Name = "lblNameCaption";
            this.lblNameCaption.Size = new System.Drawing.Size(91, 13);
            this.lblNameCaption.TabIndex = 0;
            this.lblNameCaption.Text = "Name : Zack Zou";
            // 
            // lblName
            // 
            this.lblName.AutoSize = true;
            this.lblName.Location = new System.Drawing.Point(111, 10);
            this.lblName.Margin = new System.Windows.Forms.Padding(0, 10, 0, 10);
            this.lblName.Name = "lblName";
            this.lblName.Size = new System.Drawing.Size(0, 13);
            this.lblName.TabIndex = 1;
            // 
            // flpAuthorFace
            // 
            this.flpAuthorFace.Controls.Add(this.picAuthor);
            this.flpAuthorFace.Dock = System.Windows.Forms.DockStyle.Left;
            this.flpAuthorFace.Location = new System.Drawing.Point(0, 0);
            this.flpAuthorFace.Name = "flpAuthorFace";
            this.flpAuthorFace.Size = new System.Drawing.Size(102, 104);
            this.flpAuthorFace.TabIndex = 2;
            // 
            // picAuthor
            // 
            this.picAuthor.Dock = System.Windows.Forms.DockStyle.Top;
            this.picAuthor.Location = new System.Drawing.Point(2, 2);
            this.picAuthor.Margin = new System.Windows.Forms.Padding(2);
            this.picAuthor.Name = "picAuthor";
            this.picAuthor.Size = new System.Drawing.Size(100, 100);
            this.picAuthor.TabIndex = 1;
            this.picAuthor.TabStop = false;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(107, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(54, 13);
            this.label1.TabIndex = 2;
            this.label1.Text = "Zack Zou";
            // 
            // qrCodeImgControl1
            // 
            this.qrCodeImgControl1.ErrorCorrectLevel = Gma.QrCodeNet.Encoding.ErrorCorrectionLevel.M;
            this.qrCodeImgControl1.Image = ((System.Drawing.Image)(resources.GetObject("qrCodeImgControl1.Image")));
            this.qrCodeImgControl1.Location = new System.Drawing.Point(3, 3);
            this.qrCodeImgControl1.Margin = new System.Windows.Forms.Padding(2);
            this.qrCodeImgControl1.Name = "qrCodeImgControl1";
            this.qrCodeImgControl1.QuietZoneModule = Gma.QrCodeNet.Encoding.Windows.Render.QuietZoneModules.Two;
            this.qrCodeImgControl1.Size = new System.Drawing.Size(100, 100);
            this.qrCodeImgControl1.TabIndex = 0;
            this.qrCodeImgControl1.TabStop = false;
            // 
            // lblCopyrightInfo
            // 
            this.lblCopyrightInfo.AutoSize = true;
            this.lblCopyrightInfo.Location = new System.Drawing.Point(10, 10);
            this.lblCopyrightInfo.Margin = new System.Windows.Forms.Padding(10);
            this.lblCopyrightInfo.Name = "lblCopyrightInfo";
            this.lblCopyrightInfo.Size = new System.Drawing.Size(93, 13);
            this.lblCopyrightInfo.TabIndex = 2;
            this.lblCopyrightInfo.Text = "Copyright ©  2013";
            // 
            // DefaultForm
            // 
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(800, 600);
            this.Controls.Add(this.CopyrightCaption);
            this.Controls.Add(this.VersionCaption);
            this.Controls.Add(this.AuthorCaption);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.Name = "DefaultForm";
            this.Text = "About Page";
            this.VersionCaption.ResumeLayout(false);
            this.pnlVersion.ResumeLayout(false);
            this.flpVersionInfo.ResumeLayout(false);
            this.flpVersionInfo.PerformLayout();
            this.flpVersionFace.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.picVersion)).EndInit();
            this.CopyrightCaption.ResumeLayout(false);
            this.pnlCopyright.ResumeLayout(false);
            this.flpCopyrightInfo.ResumeLayout(false);
            this.flpCopyrightInfo.PerformLayout();
            this.flpCopyrightFace.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.qriCopyright)).EndInit();
            this.AuthorCaption.ResumeLayout(false);
            this.pnlAuthor.ResumeLayout(false);
            this.flpAuthorInfo.ResumeLayout(false);
            this.flpAuthorInfo.PerformLayout();
            this.flpAuthorFace.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.picAuthor)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.qrCodeImgControl1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private Common.BaseControl.CaptionPanel VersionCaption;
        private Common.BaseControl.CaptionPanel CopyrightCaption;
        private Common.BaseControl.CaptionPanel AuthorCaption;
        private System.Windows.Forms.Panel pnlAuthor;
        private System.Windows.Forms.FlowLayoutPanel flpAuthorInfo;
        private System.Windows.Forms.PictureBox picAuthor;
        private System.Windows.Forms.Panel pnlVersion;
        private System.Windows.Forms.FlowLayoutPanel flpVersionInfo;
        private System.Windows.Forms.PictureBox picVersion;
        private System.Windows.Forms.Panel pnlCopyright;
        private System.Windows.Forms.FlowLayoutPanel flpCopyrightInfo;
        private System.Windows.Forms.Label label1;
        private Gma.QrCodeNet.Encoding.Windows.Forms.QrCodeImgControl qriCopyright;
        private Gma.QrCodeNet.Encoding.Windows.Forms.QrCodeImgControl qrCodeImgControl1;
        private System.Windows.Forms.FlowLayoutPanel flpAuthorFace;
        private System.Windows.Forms.FlowLayoutPanel flpVersionFace;
        private System.Windows.Forms.FlowLayoutPanel flpCopyrightFace;
        private System.Windows.Forms.Label lblNameCaption;
        private System.Windows.Forms.Label lblName;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label lblCopyrightInfo;



    }
}
