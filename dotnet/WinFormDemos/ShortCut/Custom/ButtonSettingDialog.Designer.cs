namespace ShortCut
{
    partial class ButtonSettingDialog
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
            this.tabSettingContent = new System.Windows.Forms.TabControl();
            this.tpButtonConfig = new System.Windows.Forms.TabPage();
            this.gbButtonIcon = new System.Windows.Forms.GroupBox();
            this.flpButtonIcon = new System.Windows.Forms.FlowLayoutPanel();
            this.rbtnLargeIcon = new System.Windows.Forms.RadioButton();
            this.rbtnSmallIcon = new System.Windows.Forms.RadioButton();
            this.gbButtonTips = new System.Windows.Forms.GroupBox();
            this.flpButtonTips = new System.Windows.Forms.FlowLayoutPanel();
            this.rbtnRightOnIcon = new System.Windows.Forms.RadioButton();
            this.rbtnLeftOnIcon = new System.Windows.Forms.RadioButton();
            this.rbtnBottomOnIcon = new System.Windows.Forms.RadioButton();
            this.rbtnTopOnIcon = new System.Windows.Forms.RadioButton();
            this.gbButtonDisplay = new System.Windows.Forms.GroupBox();
            this.flpButtonDisplay = new System.Windows.Forms.FlowLayoutPanel();
            this.rbtnImageDisplay = new System.Windows.Forms.RadioButton();
            this.rbtnTextDisplay = new System.Windows.Forms.RadioButton();
            this.gbButtonSize = new System.Windows.Forms.GroupBox();
            this.flpButtonSize = new System.Windows.Forms.FlowLayoutPanel();
            this.lblWidth = new System.Windows.Forms.Label();
            this.numUpDownWidth = new System.Windows.Forms.NumericUpDown();
            this.lblHeight = new System.Windows.Forms.Label();
            this.numUpDownHeight = new System.Windows.Forms.NumericUpDown();
            this.gbButtonCount = new System.Windows.Forms.GroupBox();
            this.flpButtonCount = new System.Windows.Forms.FlowLayoutPanel();
            this.lblColumnCount = new System.Windows.Forms.Label();
            this.numUpDownColumn = new System.Windows.Forms.NumericUpDown();
            this.lblRowCount = new System.Windows.Forms.Label();
            this.numUpDownRow = new System.Windows.Forms.NumericUpDown();
            this.pnlContent.SuspendLayout();
            this.tabSettingContent.SuspendLayout();
            this.tpButtonConfig.SuspendLayout();
            this.gbButtonIcon.SuspendLayout();
            this.flpButtonIcon.SuspendLayout();
            this.gbButtonTips.SuspendLayout();
            this.flpButtonTips.SuspendLayout();
            this.gbButtonDisplay.SuspendLayout();
            this.flpButtonDisplay.SuspendLayout();
            this.gbButtonSize.SuspendLayout();
            this.flpButtonSize.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numUpDownWidth)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.numUpDownHeight)).BeginInit();
            this.gbButtonCount.SuspendLayout();
            this.flpButtonCount.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numUpDownColumn)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.numUpDownRow)).BeginInit();
            this.SuspendLayout();
            // 
            // pnlContent
            // 
            this.pnlContent.BackColor = System.Drawing.SystemColors.GradientInactiveCaption;
            this.pnlContent.Controls.Add(this.tabSettingContent);
            // 
            // tabSettingContent
            // 
            this.tabSettingContent.Controls.Add(this.tpButtonConfig);
            this.tabSettingContent.Cursor = System.Windows.Forms.Cursors.Default;
            this.tabSettingContent.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tabSettingContent.HotTrack = true;
            this.tabSettingContent.ItemSize = new System.Drawing.Size(40, 15);
            this.tabSettingContent.Location = new System.Drawing.Point(0, 0);
            this.tabSettingContent.Margin = new System.Windows.Forms.Padding(0);
            this.tabSettingContent.Name = "tabSettingContent";
            this.tabSettingContent.Padding = new System.Drawing.Point(0, 0);
            this.tabSettingContent.SelectedIndex = 0;
            this.tabSettingContent.Size = new System.Drawing.Size(300, 250);
            this.tabSettingContent.TabIndex = 0;
            // 
            // tpButtonConfig
            // 
            this.tpButtonConfig.Controls.Add(this.gbButtonIcon);
            this.tpButtonConfig.Controls.Add(this.gbButtonTips);
            this.tpButtonConfig.Controls.Add(this.gbButtonDisplay);
            this.tpButtonConfig.Controls.Add(this.gbButtonSize);
            this.tpButtonConfig.Controls.Add(this.gbButtonCount);
            this.tpButtonConfig.Location = new System.Drawing.Point(4, 19);
            this.tpButtonConfig.Margin = new System.Windows.Forms.Padding(0);
            this.tpButtonConfig.Name = "tpButtonConfig";
            this.tpButtonConfig.Size = new System.Drawing.Size(292, 227);
            this.tpButtonConfig.TabIndex = 0;
            this.tpButtonConfig.Text = "Button";
            this.tpButtonConfig.UseVisualStyleBackColor = true;
            // 
            // gbButtonIcon
            // 
            this.gbButtonIcon.Controls.Add(this.flpButtonIcon);
            this.gbButtonIcon.Location = new System.Drawing.Point(134, 150);
            this.gbButtonIcon.Name = "gbButtonIcon";
            this.gbButtonIcon.Size = new System.Drawing.Size(156, 75);
            this.gbButtonIcon.TabIndex = 7;
            this.gbButtonIcon.TabStop = false;
            this.gbButtonIcon.Text = "Icon";
            // 
            // flpButtonIcon
            // 
            this.flpButtonIcon.Controls.Add(this.rbtnLargeIcon);
            this.flpButtonIcon.Controls.Add(this.rbtnSmallIcon);
            this.flpButtonIcon.Dock = System.Windows.Forms.DockStyle.Fill;
            this.flpButtonIcon.Location = new System.Drawing.Point(3, 16);
            this.flpButtonIcon.Name = "flpButtonIcon";
            this.flpButtonIcon.Size = new System.Drawing.Size(150, 56);
            this.flpButtonIcon.TabIndex = 0;
            // 
            // rbtnLargeIcon
            // 
            this.rbtnLargeIcon.AutoSize = true;
            this.rbtnLargeIcon.Checked = true;
            this.flpButtonIcon.SetFlowBreak(this.rbtnLargeIcon, true);
            this.rbtnLargeIcon.Location = new System.Drawing.Point(5, 5);
            this.rbtnLargeIcon.Margin = new System.Windows.Forms.Padding(5);
            this.rbtnLargeIcon.Name = "rbtnLargeIcon";
            this.rbtnLargeIcon.Size = new System.Drawing.Size(52, 17);
            this.rbtnLargeIcon.TabIndex = 1;
            this.rbtnLargeIcon.TabStop = true;
            this.rbtnLargeIcon.Text = "Large";
            this.rbtnLargeIcon.UseVisualStyleBackColor = false;
            // 
            // rbtnSmallIcon
            // 
            this.rbtnSmallIcon.AutoSize = true;
            this.rbtnSmallIcon.Location = new System.Drawing.Point(5, 32);
            this.rbtnSmallIcon.Margin = new System.Windows.Forms.Padding(5);
            this.rbtnSmallIcon.Name = "rbtnSmallIcon";
            this.rbtnSmallIcon.Size = new System.Drawing.Size(50, 17);
            this.rbtnSmallIcon.TabIndex = 2;
            this.rbtnSmallIcon.Text = "Small";
            this.rbtnSmallIcon.UseVisualStyleBackColor = false;
            // 
            // gbButtonTips
            // 
            this.gbButtonTips.Controls.Add(this.flpButtonTips);
            this.gbButtonTips.Location = new System.Drawing.Point(134, 0);
            this.gbButtonTips.Name = "gbButtonTips";
            this.gbButtonTips.Size = new System.Drawing.Size(156, 150);
            this.gbButtonTips.TabIndex = 7;
            this.gbButtonTips.TabStop = false;
            this.gbButtonTips.Text = "Tips";
            // 
            // flpButtonTips
            // 
            this.flpButtonTips.Controls.Add(this.rbtnRightOnIcon);
            this.flpButtonTips.Controls.Add(this.rbtnLeftOnIcon);
            this.flpButtonTips.Controls.Add(this.rbtnBottomOnIcon);
            this.flpButtonTips.Controls.Add(this.rbtnTopOnIcon);
            this.flpButtonTips.Dock = System.Windows.Forms.DockStyle.Fill;
            this.flpButtonTips.Location = new System.Drawing.Point(3, 16);
            this.flpButtonTips.Name = "flpButtonTips";
            this.flpButtonTips.Size = new System.Drawing.Size(150, 131);
            this.flpButtonTips.TabIndex = 0;
            // 
            // rbtnRightOnIcon
            // 
            this.rbtnRightOnIcon.AutoSize = true;
            this.flpButtonTips.SetFlowBreak(this.rbtnRightOnIcon, true);
            this.rbtnRightOnIcon.Location = new System.Drawing.Point(8, 8);
            this.rbtnRightOnIcon.Margin = new System.Windows.Forms.Padding(8);
            this.rbtnRightOnIcon.Name = "rbtnRightOnIcon";
            this.rbtnRightOnIcon.Size = new System.Drawing.Size(50, 17);
            this.rbtnRightOnIcon.TabIndex = 1;
            this.rbtnRightOnIcon.Text = "Right";
            this.rbtnRightOnIcon.UseVisualStyleBackColor = false;
            // 
            // rbtnLeftOnIcon
            // 
            this.rbtnLeftOnIcon.AutoSize = true;
            this.flpButtonTips.SetFlowBreak(this.rbtnLeftOnIcon, true);
            this.rbtnLeftOnIcon.Location = new System.Drawing.Point(8, 41);
            this.rbtnLeftOnIcon.Margin = new System.Windows.Forms.Padding(8);
            this.rbtnLeftOnIcon.Name = "rbtnLeftOnIcon";
            this.rbtnLeftOnIcon.Size = new System.Drawing.Size(43, 17);
            this.rbtnLeftOnIcon.TabIndex = 2;
            this.rbtnLeftOnIcon.Text = "Left";
            this.rbtnLeftOnIcon.UseVisualStyleBackColor = false;
            // 
            // rbtnBottomOnIcon
            // 
            this.rbtnBottomOnIcon.AutoSize = true;
            this.rbtnBottomOnIcon.Checked = true;
            this.flpButtonTips.SetFlowBreak(this.rbtnBottomOnIcon, true);
            this.rbtnBottomOnIcon.Location = new System.Drawing.Point(8, 74);
            this.rbtnBottomOnIcon.Margin = new System.Windows.Forms.Padding(8);
            this.rbtnBottomOnIcon.Name = "rbtnBottomOnIcon";
            this.rbtnBottomOnIcon.Size = new System.Drawing.Size(58, 17);
            this.rbtnBottomOnIcon.TabIndex = 3;
            this.rbtnBottomOnIcon.TabStop = true;
            this.rbtnBottomOnIcon.Text = "Bottom";
            this.rbtnBottomOnIcon.UseVisualStyleBackColor = false;
            // 
            // rbtnTopOnIcon
            // 
            this.rbtnTopOnIcon.AutoSize = true;
            this.rbtnTopOnIcon.Location = new System.Drawing.Point(8, 107);
            this.rbtnTopOnIcon.Margin = new System.Windows.Forms.Padding(8);
            this.rbtnTopOnIcon.Name = "rbtnTopOnIcon";
            this.rbtnTopOnIcon.Size = new System.Drawing.Size(44, 17);
            this.rbtnTopOnIcon.TabIndex = 4;
            this.rbtnTopOnIcon.Text = "Top";
            this.rbtnTopOnIcon.UseVisualStyleBackColor = false;
            // 
            // gbButtonDisplay
            // 
            this.gbButtonDisplay.Controls.Add(this.flpButtonDisplay);
            this.gbButtonDisplay.Location = new System.Drawing.Point(0, 150);
            this.gbButtonDisplay.Name = "gbButtonDisplay";
            this.gbButtonDisplay.Size = new System.Drawing.Size(130, 75);
            this.gbButtonDisplay.TabIndex = 6;
            this.gbButtonDisplay.TabStop = false;
            this.gbButtonDisplay.Text = "Display";
            // 
            // flpButtonDisplay
            // 
            this.flpButtonDisplay.Controls.Add(this.rbtnImageDisplay);
            this.flpButtonDisplay.Controls.Add(this.rbtnTextDisplay);
            this.flpButtonDisplay.Dock = System.Windows.Forms.DockStyle.Fill;
            this.flpButtonDisplay.Location = new System.Drawing.Point(3, 16);
            this.flpButtonDisplay.Name = "flpButtonDisplay";
            this.flpButtonDisplay.Size = new System.Drawing.Size(124, 56);
            this.flpButtonDisplay.TabIndex = 0;
            // 
            // rbtnImageDisplay
            // 
            this.rbtnImageDisplay.AutoSize = true;
            this.rbtnImageDisplay.Checked = true;
            this.flpButtonDisplay.SetFlowBreak(this.rbtnImageDisplay, true);
            this.rbtnImageDisplay.Location = new System.Drawing.Point(5, 5);
            this.rbtnImageDisplay.Margin = new System.Windows.Forms.Padding(5);
            this.rbtnImageDisplay.Name = "rbtnImageDisplay";
            this.rbtnImageDisplay.Size = new System.Drawing.Size(54, 17);
            this.rbtnImageDisplay.TabIndex = 0;
            this.rbtnImageDisplay.TabStop = true;
            this.rbtnImageDisplay.Text = "Image";
            this.rbtnImageDisplay.UseVisualStyleBackColor = false;
            // 
            // rbtnTextDisplay
            // 
            this.rbtnTextDisplay.AutoSize = true;
            this.rbtnTextDisplay.Location = new System.Drawing.Point(5, 32);
            this.rbtnTextDisplay.Margin = new System.Windows.Forms.Padding(5);
            this.rbtnTextDisplay.Name = "rbtnTextDisplay";
            this.rbtnTextDisplay.Size = new System.Drawing.Size(46, 17);
            this.rbtnTextDisplay.TabIndex = 1;
            this.rbtnTextDisplay.Text = "Text";
            this.rbtnTextDisplay.UseVisualStyleBackColor = false;
            // 
            // gbButtonSize
            // 
            this.gbButtonSize.Controls.Add(this.flpButtonSize);
            this.gbButtonSize.Location = new System.Drawing.Point(0, 75);
            this.gbButtonSize.Name = "gbButtonSize";
            this.gbButtonSize.Size = new System.Drawing.Size(130, 75);
            this.gbButtonSize.TabIndex = 5;
            this.gbButtonSize.TabStop = false;
            this.gbButtonSize.Text = "Size";
            // 
            // flpButtonSize
            // 
            this.flpButtonSize.Controls.Add(this.lblWidth);
            this.flpButtonSize.Controls.Add(this.numUpDownWidth);
            this.flpButtonSize.Controls.Add(this.lblHeight);
            this.flpButtonSize.Controls.Add(this.numUpDownHeight);
            this.flpButtonSize.Dock = System.Windows.Forms.DockStyle.Fill;
            this.flpButtonSize.Location = new System.Drawing.Point(3, 16);
            this.flpButtonSize.Name = "flpButtonSize";
            this.flpButtonSize.Size = new System.Drawing.Size(124, 56);
            this.flpButtonSize.TabIndex = 4;
            // 
            // lblWidth
            // 
            this.lblWidth.AutoSize = true;
            this.lblWidth.Location = new System.Drawing.Point(21, 8);
            this.lblWidth.Margin = new System.Windows.Forms.Padding(21, 8, 8, 8);
            this.lblWidth.Name = "lblWidth";
            this.lblWidth.Size = new System.Drawing.Size(35, 13);
            this.lblWidth.TabIndex = 2;
            this.lblWidth.Text = "Width";
            this.lblWidth.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // numUpDownWidth
            // 
            this.flpButtonSize.SetFlowBreak(this.numUpDownWidth, true);
            this.numUpDownWidth.Location = new System.Drawing.Point(68, 4);
            this.numUpDownWidth.Margin = new System.Windows.Forms.Padding(4);
            this.numUpDownWidth.Minimum = new decimal(new int[] {
            20,
            0,
            0,
            0});
            this.numUpDownWidth.Name = "numUpDownWidth";
            this.numUpDownWidth.Size = new System.Drawing.Size(50, 20);
            this.numUpDownWidth.TabIndex = 3;
            this.numUpDownWidth.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.numUpDownWidth.Value = new decimal(new int[] {
            20,
            0,
            0,
            0});
            // 
            // lblHeight
            // 
            this.lblHeight.AutoSize = true;
            this.lblHeight.Location = new System.Drawing.Point(18, 37);
            this.lblHeight.Margin = new System.Windows.Forms.Padding(18, 8, 8, 8);
            this.lblHeight.Name = "lblHeight";
            this.lblHeight.Size = new System.Drawing.Size(38, 13);
            this.lblHeight.TabIndex = 1;
            this.lblHeight.Text = "Height";
            this.lblHeight.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // numUpDownHeight
            // 
            this.numUpDownHeight.Location = new System.Drawing.Point(68, 33);
            this.numUpDownHeight.Margin = new System.Windows.Forms.Padding(4);
            this.numUpDownHeight.Minimum = new decimal(new int[] {
            20,
            0,
            0,
            0});
            this.numUpDownHeight.Name = "numUpDownHeight";
            this.numUpDownHeight.Size = new System.Drawing.Size(50, 20);
            this.numUpDownHeight.TabIndex = 3;
            this.numUpDownHeight.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.numUpDownHeight.Value = new decimal(new int[] {
            20,
            0,
            0,
            0});
            // 
            // gbButtonCount
            // 
            this.gbButtonCount.Controls.Add(this.flpButtonCount);
            this.gbButtonCount.Location = new System.Drawing.Point(0, 0);
            this.gbButtonCount.Name = "gbButtonCount";
            this.gbButtonCount.Size = new System.Drawing.Size(130, 75);
            this.gbButtonCount.TabIndex = 0;
            this.gbButtonCount.TabStop = false;
            this.gbButtonCount.Text = "Count";
            // 
            // flpButtonCount
            // 
            this.flpButtonCount.Controls.Add(this.lblColumnCount);
            this.flpButtonCount.Controls.Add(this.numUpDownColumn);
            this.flpButtonCount.Controls.Add(this.lblRowCount);
            this.flpButtonCount.Controls.Add(this.numUpDownRow);
            this.flpButtonCount.Dock = System.Windows.Forms.DockStyle.Fill;
            this.flpButtonCount.Location = new System.Drawing.Point(3, 16);
            this.flpButtonCount.Name = "flpButtonCount";
            this.flpButtonCount.Size = new System.Drawing.Size(124, 56);
            this.flpButtonCount.TabIndex = 4;
            // 
            // lblColumnCount
            // 
            this.lblColumnCount.AutoSize = true;
            this.lblColumnCount.Location = new System.Drawing.Point(8, 8);
            this.lblColumnCount.Margin = new System.Windows.Forms.Padding(8);
            this.lblColumnCount.Name = "lblColumnCount";
            this.lblColumnCount.Size = new System.Drawing.Size(47, 13);
            this.lblColumnCount.TabIndex = 2;
            this.lblColumnCount.Text = "Columns";
            this.lblColumnCount.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // numUpDownColumn
            // 
            this.flpButtonCount.SetFlowBreak(this.numUpDownColumn, true);
            this.numUpDownColumn.Location = new System.Drawing.Point(67, 4);
            this.numUpDownColumn.Margin = new System.Windows.Forms.Padding(4);
            this.numUpDownColumn.Minimum = new decimal(new int[] {
            5,
            0,
            0,
            0});
            this.numUpDownColumn.Name = "numUpDownColumn";
            this.numUpDownColumn.Size = new System.Drawing.Size(50, 20);
            this.numUpDownColumn.TabIndex = 3;
            this.numUpDownColumn.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.numUpDownColumn.Value = new decimal(new int[] {
            5,
            0,
            0,
            0});
            // 
            // lblRowCount
            // 
            this.lblRowCount.AutoSize = true;
            this.lblRowCount.Location = new System.Drawing.Point(21, 37);
            this.lblRowCount.Margin = new System.Windows.Forms.Padding(21, 8, 8, 8);
            this.lblRowCount.Name = "lblRowCount";
            this.lblRowCount.Size = new System.Drawing.Size(34, 13);
            this.lblRowCount.TabIndex = 1;
            this.lblRowCount.Text = "Rows";
            this.lblRowCount.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // numUpDownRow
            // 
            this.numUpDownRow.Location = new System.Drawing.Point(67, 33);
            this.numUpDownRow.Margin = new System.Windows.Forms.Padding(4);
            this.numUpDownRow.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.numUpDownRow.Name = "numUpDownRow";
            this.numUpDownRow.Size = new System.Drawing.Size(50, 20);
            this.numUpDownRow.TabIndex = 3;
            this.numUpDownRow.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.numUpDownRow.Value = new decimal(new int[] {
            5,
            0,
            0,
            0});
            // 
            // ButtonSettingDialog
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.ClientSize = new System.Drawing.Size(300, 300);
            this.Name = "ButtonSettingDialog";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.pnlContent.ResumeLayout(false);
            this.tabSettingContent.ResumeLayout(false);
            this.tpButtonConfig.ResumeLayout(false);
            this.gbButtonIcon.ResumeLayout(false);
            this.flpButtonIcon.ResumeLayout(false);
            this.flpButtonIcon.PerformLayout();
            this.gbButtonTips.ResumeLayout(false);
            this.flpButtonTips.ResumeLayout(false);
            this.flpButtonTips.PerformLayout();
            this.gbButtonDisplay.ResumeLayout(false);
            this.flpButtonDisplay.ResumeLayout(false);
            this.flpButtonDisplay.PerformLayout();
            this.gbButtonSize.ResumeLayout(false);
            this.flpButtonSize.ResumeLayout(false);
            this.flpButtonSize.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numUpDownWidth)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.numUpDownHeight)).EndInit();
            this.gbButtonCount.ResumeLayout(false);
            this.flpButtonCount.ResumeLayout(false);
            this.flpButtonCount.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numUpDownColumn)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.numUpDownRow)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabControl tabSettingContent;
        private System.Windows.Forms.TabPage tpButtonConfig;
        private System.Windows.Forms.Label lblColumnCount;
        private System.Windows.Forms.Label lblRowCount;
        private System.Windows.Forms.FlowLayoutPanel flpButtonCount;
        private System.Windows.Forms.GroupBox gbButtonCount;
        private System.Windows.Forms.GroupBox gbButtonSize;
        private System.Windows.Forms.FlowLayoutPanel flpButtonSize;
        private System.Windows.Forms.Label lblWidth;
        private System.Windows.Forms.Label lblHeight;
        private System.Windows.Forms.GroupBox gbButtonDisplay;
        private System.Windows.Forms.FlowLayoutPanel flpButtonDisplay;
        private System.Windows.Forms.GroupBox gbButtonIcon;
        private System.Windows.Forms.FlowLayoutPanel flpButtonIcon;
        private System.Windows.Forms.GroupBox gbButtonTips;
        private System.Windows.Forms.FlowLayoutPanel flpButtonTips;
        private System.Windows.Forms.NumericUpDown numUpDownRow;
        private System.Windows.Forms.NumericUpDown numUpDownWidth;
        private System.Windows.Forms.NumericUpDown numUpDownHeight;
        private System.Windows.Forms.NumericUpDown numUpDownColumn;
        private System.Windows.Forms.RadioButton rbtnLargeIcon;
        private System.Windows.Forms.RadioButton rbtnSmallIcon;
        private System.Windows.Forms.RadioButton rbtnRightOnIcon;
        private System.Windows.Forms.RadioButton rbtnLeftOnIcon;
        private System.Windows.Forms.RadioButton rbtnBottomOnIcon;
        private System.Windows.Forms.RadioButton rbtnTopOnIcon;
        private System.Windows.Forms.RadioButton rbtnImageDisplay;
        private System.Windows.Forms.RadioButton rbtnTextDisplay;

    }
}
