namespace BigFrame
{
    partial class MainForm
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
            this.components = new System.ComponentModel.Container();
            this.splitContainer = new System.Windows.Forms.SplitContainer();
            this.dockingTab = new Common.BaseControl.DockingTab();
            this.splitterToolForm = new System.Windows.Forms.Splitter();
            this.splitterLeftMenu = new System.Windows.Forms.Splitter();
            this.pnlButtonPanel = new System.Windows.Forms.Panel();
            this.picDockFlag = new System.Windows.Forms.PictureBox();
            this.btnFavoriteInfo = new System.Windows.Forms.Button();
            this.btnHistoryInfo = new System.Windows.Forms.Button();
            this.btnOperatorInfo = new System.Windows.Forms.Button();
            this.toolFormTimer = new System.Windows.Forms.Timer(this.components);
            this.popMenu = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.popMenuClose = new System.Windows.Forms.ToolStripMenuItem();
            this.popMenuCloseOther = new System.Windows.Forms.ToolStripMenuItem();
            this.popMenuCloseAllPage = new System.Windows.Forms.ToolStripMenuItem();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer)).BeginInit();
            this.splitContainer.Panel2.SuspendLayout();
            this.splitContainer.SuspendLayout();
            this.pnlButtonPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picDockFlag)).BeginInit();
            this.popMenu.SuspendLayout();
            this.SuspendLayout();
            // 
            // pnlCaption
            // 
            this.pnlCaption.Size = new System.Drawing.Size(1024, 25);
            // 
            // splitContainer
            // 
            this.splitContainer.BackColor = System.Drawing.Color.White;
            this.splitContainer.Dock = System.Windows.Forms.DockStyle.Fill;
            this.splitContainer.IsSplitterFixed = true;
            this.splitContainer.Location = new System.Drawing.Point(25, 25);
            this.splitContainer.Margin = new System.Windows.Forms.Padding(0);
            this.splitContainer.Name = "splitContainer";
            this.splitContainer.Panel1Collapsed = true;
            this.splitContainer.Panel1MinSize = 0;
            // 
            // splitContainer.Panel2
            // 
            this.splitContainer.Panel2.Controls.Add(this.dockingTab);
            this.splitContainer.Panel2.Controls.Add(this.splitterToolForm);
            this.splitContainer.Panel2MinSize = 0;
            this.splitContainer.Size = new System.Drawing.Size(999, 743);
            this.splitContainer.SplitterDistance = 25;
            this.splitContainer.SplitterWidth = 1;
            this.splitContainer.TabIndex = 0;
            // 
            // dockingTab
            // 
            this.dockingTab.AllowDrop = true;
            this.dockingTab.ButtonLabelBackColor = System.Drawing.Color.LightSkyBlue;
            this.dockingTab.ButtonLabelFocusBackColor = System.Drawing.Color.BlueViolet;
            this.dockingTab.ButtonLabelForeColor = System.Drawing.Color.White;
            this.dockingTab.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dockingTab.Editable = true;
            this.dockingTab.EnphasisColor = System.Drawing.Color.Yellow;
            this.dockingTab.ErrorBackColor = System.Drawing.Color.SeaShell;
            this.dockingTab.Foldable = false;
            this.dockingTab.GotFocusBackColor = System.Drawing.Color.Orange;
            this.dockingTab.GotFocusLabelForeColor = System.Drawing.Color.White;
            this.dockingTab.HoverControl = null;
            this.dockingTab.LabelFont = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.dockingTab.LabelForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(102)))), ((int)(((byte)(0)))));
            this.dockingTab.Location = new System.Drawing.Point(1, 0);
            this.dockingTab.Name = "dockingTab";
            this.dockingTab.SelectedTabControl = null;
            this.dockingTab.SelectedTabIndex = -1;
            this.dockingTab.Size = new System.Drawing.Size(998, 743);
            this.dockingTab.TabIndex = 7;
            this.dockingTab.WaitCounter = 0;
            this.dockingTab.Waiting = false;
            this.dockingTab.WarningBackColor = System.Drawing.Color.LightYellow;
            this.dockingTab.ControlAdded += new System.Windows.Forms.ControlEventHandler(this.dockingTab_ControlAdded);
            this.dockingTab.ControlRemoved += new System.Windows.Forms.ControlEventHandler(this.dockingTab_ControlRemoved);
            this.dockingTab.MouseDown += new System.Windows.Forms.MouseEventHandler(this.dockingTab_MouseDown);
            this.dockingTab.MouseUp += new System.Windows.Forms.MouseEventHandler(this.dockingTab_MouseUp);
            // 
            // splitterToolForm
            // 
            this.splitterToolForm.BackColor = System.Drawing.Color.White;
            this.splitterToolForm.Location = new System.Drawing.Point(0, 0);
            this.splitterToolForm.Margin = new System.Windows.Forms.Padding(0);
            this.splitterToolForm.Name = "splitterToolForm";
            this.splitterToolForm.Size = new System.Drawing.Size(1, 743);
            this.splitterToolForm.TabIndex = 6;
            this.splitterToolForm.TabStop = false;
            // 
            // splitterLeftMenu
            // 
            this.splitterLeftMenu.BackColor = System.Drawing.Color.White;
            this.splitterLeftMenu.Location = new System.Drawing.Point(0, 25);
            this.splitterLeftMenu.Margin = new System.Windows.Forms.Padding(0);
            this.splitterLeftMenu.MinExtra = 0;
            this.splitterLeftMenu.MinSize = 0;
            this.splitterLeftMenu.Name = "splitterLeftMenu";
            this.splitterLeftMenu.Size = new System.Drawing.Size(25, 743);
            this.splitterLeftMenu.TabIndex = 0;
            this.splitterLeftMenu.TabStop = false;
            // 
            // pnlButtonPanel
            // 
            this.pnlButtonPanel.BackColor = System.Drawing.Color.White;
            this.pnlButtonPanel.Controls.Add(this.picDockFlag);
            this.pnlButtonPanel.Controls.Add(this.btnFavoriteInfo);
            this.pnlButtonPanel.Controls.Add(this.btnHistoryInfo);
            this.pnlButtonPanel.Controls.Add(this.btnOperatorInfo);
            this.pnlButtonPanel.Location = new System.Drawing.Point(0, 25);
            this.pnlButtonPanel.Margin = new System.Windows.Forms.Padding(0);
            this.pnlButtonPanel.Name = "pnlButtonPanel";
            this.pnlButtonPanel.Size = new System.Drawing.Size(25, 350);
            this.pnlButtonPanel.TabIndex = 0;
            // 
            // picDockFlag
            // 
            this.picDockFlag.BackColor = System.Drawing.Color.White;
            this.picDockFlag.Cursor = System.Windows.Forms.Cursors.Hand;
            this.picDockFlag.Location = new System.Drawing.Point(4, 4);
            this.picDockFlag.Margin = new System.Windows.Forms.Padding(0);
            this.picDockFlag.Name = "picDockFlag";
            this.picDockFlag.Size = new System.Drawing.Size(16, 15);
            this.picDockFlag.TabIndex = 4;
            this.picDockFlag.TabStop = false;
            this.picDockFlag.Click += new System.EventHandler(this.picDockFlag_Click);
            // 
            // btnFavoriteInfo
            // 
            this.btnFavoriteInfo.BackColor = System.Drawing.Color.LightSkyBlue;
            this.btnFavoriteInfo.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnFavoriteInfo.FlatAppearance.BorderSize = 0;
            this.btnFavoriteInfo.FlatAppearance.MouseDownBackColor = System.Drawing.Color.BlueViolet;
            this.btnFavoriteInfo.FlatAppearance.MouseOverBackColor = System.Drawing.Color.BlueViolet;
            this.btnFavoriteInfo.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnFavoriteInfo.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnFavoriteInfo.ForeColor = System.Drawing.Color.White;
            this.btnFavoriteInfo.Location = new System.Drawing.Point(0, 22);
            this.btnFavoriteInfo.Margin = new System.Windows.Forms.Padding(1);
            this.btnFavoriteInfo.Name = "btnFavoriteInfo";
            this.btnFavoriteInfo.Padding = new System.Windows.Forms.Padding(0, 3, 3, 3);
            this.btnFavoriteInfo.Size = new System.Drawing.Size(26, 110);
            this.btnFavoriteInfo.TabIndex = 0;
            this.btnFavoriteInfo.Text = "收  藏  夹";
            this.btnFavoriteInfo.UseVisualStyleBackColor = false;
            this.btnFavoriteInfo.Click += new System.EventHandler(this.btnFavoriteInfo_Click);
            // 
            // btnHistoryInfo
            // 
            this.btnHistoryInfo.BackColor = System.Drawing.Color.LightSkyBlue;
            this.btnHistoryInfo.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnHistoryInfo.FlatAppearance.BorderSize = 0;
            this.btnHistoryInfo.FlatAppearance.MouseDownBackColor = System.Drawing.Color.BlueViolet;
            this.btnHistoryInfo.FlatAppearance.MouseOverBackColor = System.Drawing.Color.BlueViolet;
            this.btnHistoryInfo.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnHistoryInfo.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnHistoryInfo.ForeColor = System.Drawing.Color.White;
            this.btnHistoryInfo.Location = new System.Drawing.Point(0, 246);
            this.btnHistoryInfo.Margin = new System.Windows.Forms.Padding(1);
            this.btnHistoryInfo.Name = "btnHistoryInfo";
            this.btnHistoryInfo.Size = new System.Drawing.Size(26, 110);
            this.btnHistoryInfo.TabIndex = 2;
            this.btnHistoryInfo.Text = "历  史  记  录";
            this.btnHistoryInfo.UseVisualStyleBackColor = false;
            this.btnHistoryInfo.Click += new System.EventHandler(this.btnHistoryInfo_Click);
            // 
            // btnOperatorInfo
            // 
            this.btnOperatorInfo.BackColor = System.Drawing.Color.LightSkyBlue;
            this.btnOperatorInfo.Cursor = System.Windows.Forms.Cursors.Hand;
            this.btnOperatorInfo.FlatAppearance.BorderSize = 0;
            this.btnOperatorInfo.FlatAppearance.MouseDownBackColor = System.Drawing.Color.BlueViolet;
            this.btnOperatorInfo.FlatAppearance.MouseOverBackColor = System.Drawing.Color.BlueViolet;
            this.btnOperatorInfo.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnOperatorInfo.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnOperatorInfo.ForeColor = System.Drawing.Color.White;
            this.btnOperatorInfo.Location = new System.Drawing.Point(0, 134);
            this.btnOperatorInfo.Margin = new System.Windows.Forms.Padding(1);
            this.btnOperatorInfo.Name = "btnOperatorInfo";
            this.btnOperatorInfo.Padding = new System.Windows.Forms.Padding(0, 3, 3, 3);
            this.btnOperatorInfo.Size = new System.Drawing.Size(26, 110);
            this.btnOperatorInfo.TabIndex = 1;
            this.btnOperatorInfo.Text = "个  人  信   息";
            this.btnOperatorInfo.UseVisualStyleBackColor = false;
            this.btnOperatorInfo.Click += new System.EventHandler(this.btnOperatorInfo_Click);
            // 
            // toolFormTimer
            // 
            this.toolFormTimer.Interval = 800;
            this.toolFormTimer.Tick += new System.EventHandler(this.toolFormTimer_Tick);
            // 
            // popMenu
            // 
            this.popMenu.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.popMenuClose,
            this.popMenuCloseOther,
            this.popMenuCloseAllPage});
            this.popMenu.Name = "popMenu";
            this.popMenu.ShowItemToolTips = false;
            this.popMenu.Size = new System.Drawing.Size(150, 70);
            this.popMenu.PreviewKeyDown += new System.Windows.Forms.PreviewKeyDownEventHandler(this.popMenu_PreviewKeyDown);
            // 
            // popMenuClose
            // 
            this.popMenuClose.Name = "popMenuClose";
            this.popMenuClose.ShortcutKeyDisplayString = "";
            this.popMenuClose.ShowShortcutKeys = false;
            this.popMenuClose.Size = new System.Drawing.Size(149, 22);
            this.popMenuClose.Text = "Close";
            this.popMenuClose.Click += new System.EventHandler(this.popMenuClose_Click);
            // 
            // popMenuCloseOther
            // 
            this.popMenuCloseOther.Name = "popMenuCloseOther";
            this.popMenuCloseOther.ShowShortcutKeys = false;
            this.popMenuCloseOther.Size = new System.Drawing.Size(149, 22);
            this.popMenuCloseOther.Text = "Close Other";
            this.popMenuCloseOther.Click += new System.EventHandler(this.popMenuCloseOther_Click);
            // 
            // popMenuCloseAllPage
            // 
            this.popMenuCloseAllPage.Name = "popMenuCloseAllPage";
            this.popMenuCloseAllPage.Size = new System.Drawing.Size(149, 22);
            this.popMenuCloseAllPage.Text = "Close All Page";
            this.popMenuCloseAllPage.Click += new System.EventHandler(this.popMenuCloseAllPage_Click);
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(1024, 768);
            this.Controls.Add(this.pnlButtonPanel);
            this.Controls.Add(this.splitContainer);
            this.Controls.Add(this.splitterLeftMenu);
            this.MaxButtonVisable = true;
            this.MinButtonVisable = true;
            this.Name = "MainForm";
            this.Load += new System.EventHandler(this.MainForm_Load);
            this.Controls.SetChildIndex(this.pnlCaption, 0);
            this.Controls.SetChildIndex(this.splitterLeftMenu, 0);
            this.Controls.SetChildIndex(this.splitContainer, 0);
            this.Controls.SetChildIndex(this.pnlButtonPanel, 0);
            this.splitContainer.Panel2.ResumeLayout(false);
            this.splitContainer.Panel2.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer)).EndInit();
            this.splitContainer.ResumeLayout(false);
            this.pnlButtonPanel.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.picDockFlag)).EndInit();
            this.popMenu.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.SplitContainer splitContainer;
        private System.Windows.Forms.Splitter splitterToolForm;
        private System.Windows.Forms.Splitter splitterLeftMenu;
        private System.Windows.Forms.Panel pnlButtonPanel;
        private Common.BaseControl.DockingTab dockingTab;
        private System.Windows.Forms.Button btnFavoriteInfo;
        private System.Windows.Forms.Button btnHistoryInfo;
        private System.Windows.Forms.Button btnOperatorInfo;
        private System.Windows.Forms.Timer toolFormTimer;
        private System.Windows.Forms.ContextMenuStrip popMenu;
        private System.Windows.Forms.ToolStripMenuItem popMenuClose;
        private System.Windows.Forms.ToolStripMenuItem popMenuCloseOther;
        private System.Windows.Forms.ToolStripMenuItem popMenuCloseAllPage;
        private System.Windows.Forms.PictureBox picDockFlag;
    }
}

