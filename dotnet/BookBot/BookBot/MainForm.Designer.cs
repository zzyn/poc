namespace BookBot
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(MainForm));
            this.bcplData = new Common.BaseControl.BlockCaptionPanel();
            this.dockTabData = new Common.BaseControl.DockingTab();
            this.tpMatch = new Common.BaseControl.TabPanel();
            this.pnlMatch = new System.Windows.Forms.Panel();
            this.dgvMatch = new System.Windows.Forms.DataGridView();
            this.tpDiff = new Common.BaseControl.TabPanel();
            this.pnlDiff = new System.Windows.Forms.Panel();
            this.dgvDiff = new System.Windows.Forms.DataGridView();
            this.pnlToolScrip = new System.Windows.Forms.Panel();
            this.toolStripMatch = new System.Windows.Forms.ToolStrip();
            this.tsbtnDiffExport = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.tspbMatch = new System.Windows.Forms.ToolStripProgressBar();
            this.tslMatch = new System.Windows.Forms.ToolStripLabel();
            this.bcplState = new Common.BaseControl.BlockCaptionPanel();
            this.pnlLogs = new System.Windows.Forms.Panel();
            this.txtLogs = new System.Windows.Forms.TextBox();
            this.lblMessage = new System.Windows.Forms.Label();
            this.bcplSearch = new Common.BaseControl.BlockCaptionPanel();
            this.fplInput = new System.Windows.Forms.FlowLayoutPanel();
            this.flpMatchButtons = new System.Windows.Forms.FlowLayoutPanel();
            this.btnStartMatch = new System.Windows.Forms.Button();
            this.btnCancleMatch = new System.Windows.Forms.Button();
            this.tabMaster = new Common.BaseControl.TabPanel();
            this.tabMatch = new Common.BaseControl.TabPanel();
            this.txtUrl = new System.Windows.Forms.TextBox();
            this.txtBookID = new System.Windows.Forms.TextBox();
            this.bcplData.SuspendLayout();
            this.dockTabData.SuspendLayout();
            this.tpMatch.SuspendLayout();
            this.pnlMatch.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgvMatch)).BeginInit();
            this.tpDiff.SuspendLayout();
            this.pnlDiff.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dgvDiff)).BeginInit();
            this.pnlToolScrip.SuspendLayout();
            this.toolStripMatch.SuspendLayout();
            this.bcplState.SuspendLayout();
            this.pnlLogs.SuspendLayout();
            this.bcplSearch.SuspendLayout();
            this.fplInput.SuspendLayout();
            this.flpMatchButtons.SuspendLayout();
            this.SuspendLayout();
            // 
            // bcplData
            // 
            this.bcplData.ButtonLabelBackColor = System.Drawing.Color.Black;
            this.bcplData.ButtonLabelFocusBackColor = System.Drawing.Color.Orange;
            this.bcplData.ButtonLabelForeColor = System.Drawing.Color.White;
            this.bcplData.Controls.Add(this.dockTabData);
            this.bcplData.Controls.Add(this.pnlToolScrip);
            this.bcplData.Dock = System.Windows.Forms.DockStyle.Fill;
            this.bcplData.Editable = true;
            this.bcplData.EnphasisColor = System.Drawing.Color.Yellow;
            this.bcplData.ErrorBackColor = System.Drawing.Color.SeaShell;
            this.bcplData.Foldable = false;
            this.bcplData.GotFocusBackColor = System.Drawing.Color.LemonChiffon;
            this.bcplData.GotFocusLabelForeColor = System.Drawing.Color.Brown;
            this.bcplData.LabelFont = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.bcplData.LabelForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(102)))), ((int)(((byte)(0)))));
            this.bcplData.Location = new System.Drawing.Point(0, 281);
            this.bcplData.Name = "bcplData";
            this.bcplData.Size = new System.Drawing.Size(984, 376);
            this.bcplData.TabIndex = 1;
            this.bcplData.Text = "比价结果";
            this.bcplData.WaitCounter = 0;
            this.bcplData.Waiting = false;
            this.bcplData.WarningBackColor = System.Drawing.Color.LightYellow;
            // 
            // dockTabData
            // 
            this.dockTabData.AllowDrop = true;
            this.dockTabData.ButtonLabelBackColor = System.Drawing.Color.Black;
            this.dockTabData.ButtonLabelFocusBackColor = System.Drawing.Color.Orange;
            this.dockTabData.ButtonLabelForeColor = System.Drawing.Color.White;
            this.dockTabData.Controls.Add(this.tpMatch);
            this.dockTabData.Controls.Add(this.tpDiff);
            this.dockTabData.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dockTabData.Editable = true;
            this.dockTabData.EnphasisColor = System.Drawing.Color.Yellow;
            this.dockTabData.ErrorBackColor = System.Drawing.Color.SeaShell;
            this.dockTabData.Foldable = false;
            this.dockTabData.GotFocusBackColor = System.Drawing.Color.LemonChiffon;
            this.dockTabData.GotFocusLabelForeColor = System.Drawing.Color.Brown;
            this.dockTabData.HoverControl = null;
            this.dockTabData.LabelFont = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.dockTabData.LabelForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(102)))), ((int)(((byte)(0)))));
            this.dockTabData.Location = new System.Drawing.Point(10, 60);
            this.dockTabData.Name = "dockTabData";
            this.dockTabData.SelectedTabControl = this.tpMatch;
            this.dockTabData.SelectedTabIndex = 0;
            this.dockTabData.Size = new System.Drawing.Size(964, 308);
            this.dockTabData.TabIndex = 1;
            this.dockTabData.Text = "dockingTab1";
            this.dockTabData.WaitCounter = 0;
            this.dockTabData.Waiting = false;
            this.dockTabData.WarningBackColor = System.Drawing.Color.LightYellow;
            // 
            // tpMatch
            // 
            this.tpMatch.ButtonLabelBackColor = System.Drawing.Color.Black;
            this.tpMatch.ButtonLabelFocusBackColor = System.Drawing.Color.Orange;
            this.tpMatch.ButtonLabelForeColor = System.Drawing.Color.White;
            this.tpMatch.Controls.Add(this.pnlMatch);
            this.tpMatch.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tpMatch.Editable = true;
            this.tpMatch.EnphasisColor = System.Drawing.Color.Yellow;
            this.tpMatch.ErrorBackColor = System.Drawing.Color.SeaShell;
            this.tpMatch.Foldable = false;
            this.tpMatch.GotFocusBackColor = System.Drawing.Color.LemonChiffon;
            this.tpMatch.GotFocusLabelForeColor = System.Drawing.Color.Brown;
            this.tpMatch.HoverControl = null;
            this.tpMatch.IsTab = false;
            this.tpMatch.LabelFont = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.tpMatch.LabelForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(102)))), ((int)(((byte)(0)))));
            this.tpMatch.Location = new System.Drawing.Point(8, 30);
            this.tpMatch.Name = "tpMatch";
            this.tpMatch.SelectedTabControl = null;
            this.tpMatch.SelectedTabIndex = -1;
            this.tpMatch.Size = new System.Drawing.Size(948, 270);
            this.tpMatch.TabIndex = 1;
            this.tpMatch.Text = "匹配数据";
            this.tpMatch.TextVisible = false;
            this.tpMatch.WaitCounter = 0;
            this.tpMatch.Waiting = false;
            this.tpMatch.WarningBackColor = System.Drawing.Color.LightYellow;
            // 
            // pnlMatch
            // 
            this.pnlMatch.Controls.Add(this.dgvMatch);
            this.pnlMatch.Dock = System.Windows.Forms.DockStyle.Top;
            this.pnlMatch.Location = new System.Drawing.Point(3, 3);
            this.pnlMatch.Name = "pnlMatch";
            this.pnlMatch.Size = new System.Drawing.Size(942, 14);
            this.pnlMatch.TabIndex = 0;
            // 
            // dgvMatch
            // 
            this.dgvMatch.AllowUserToAddRows = false;
            this.dgvMatch.AllowUserToDeleteRows = false;
            this.dgvMatch.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvMatch.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dgvMatch.Location = new System.Drawing.Point(0, 0);
            this.dgvMatch.Name = "dgvMatch";
            this.dgvMatch.RowTemplate.Height = 23;
            this.dgvMatch.Size = new System.Drawing.Size(942, 14);
            this.dgvMatch.TabIndex = 1;
            // 
            // tpDiff
            // 
            this.tpDiff.ButtonLabelBackColor = System.Drawing.Color.Black;
            this.tpDiff.ButtonLabelFocusBackColor = System.Drawing.Color.Orange;
            this.tpDiff.ButtonLabelForeColor = System.Drawing.Color.White;
            this.tpDiff.Controls.Add(this.pnlDiff);
            this.tpDiff.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tpDiff.Editable = true;
            this.tpDiff.EnphasisColor = System.Drawing.Color.Yellow;
            this.tpDiff.ErrorBackColor = System.Drawing.Color.SeaShell;
            this.tpDiff.Foldable = false;
            this.tpDiff.GotFocusBackColor = System.Drawing.Color.LemonChiffon;
            this.tpDiff.GotFocusLabelForeColor = System.Drawing.Color.Brown;
            this.tpDiff.HoverControl = null;
            this.tpDiff.IsTab = false;
            this.tpDiff.LabelFont = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.tpDiff.LabelForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(102)))), ((int)(((byte)(0)))));
            this.tpDiff.Location = new System.Drawing.Point(8, 30);
            this.tpDiff.Name = "tpDiff";
            this.tpDiff.SelectedTabControl = null;
            this.tpDiff.SelectedTabIndex = -1;
            this.tpDiff.Size = new System.Drawing.Size(934, 270);
            this.tpDiff.TabIndex = 0;
            this.tpDiff.TextVisible = false;
            this.tpDiff.WaitCounter = 0;
            this.tpDiff.Waiting = false;
            this.tpDiff.WarningBackColor = System.Drawing.Color.LightYellow;
            // 
            // pnlDiff
            // 
            this.pnlDiff.Controls.Add(this.dgvDiff);
            this.pnlDiff.Dock = System.Windows.Forms.DockStyle.Top;
            this.pnlDiff.Location = new System.Drawing.Point(3, 3);
            this.pnlDiff.Name = "pnlDiff";
            this.pnlDiff.Size = new System.Drawing.Size(942, 14);
            this.pnlDiff.TabIndex = 0;
            // 
            // dgvDiff
            // 
            this.dgvDiff.AllowUserToAddRows = false;
            this.dgvDiff.AllowUserToDeleteRows = false;
            this.dgvDiff.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvDiff.Dock = System.Windows.Forms.DockStyle.Top;
            this.dgvDiff.Location = new System.Drawing.Point(0, 0);
            this.dgvDiff.Name = "dgvDiff";
            this.dgvDiff.RowTemplate.Height = 23;
            this.dgvDiff.Size = new System.Drawing.Size(942, 14);
            this.dgvDiff.TabIndex = 1;
            // 
            // pnlToolScrip
            // 
            this.pnlToolScrip.Controls.Add(this.toolStripMatch);
            this.pnlToolScrip.Dock = System.Windows.Forms.DockStyle.Top;
            this.pnlToolScrip.Location = new System.Drawing.Point(13, 31);
            this.pnlToolScrip.Name = "pnlToolScrip";
            this.pnlToolScrip.Size = new System.Drawing.Size(958, 26);
            this.pnlToolScrip.TabIndex = 0;
            // 
            // toolStripMatch
            // 
            this.toolStripMatch.Dock = System.Windows.Forms.DockStyle.Fill;
            this.toolStripMatch.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsbtnDiffExport,
            this.toolStripSeparator2,
            this.tspbMatch,
            this.tslMatch});
            this.toolStripMatch.Location = new System.Drawing.Point(0, 0);
            this.toolStripMatch.Name = "toolStripMatch";
            this.toolStripMatch.Size = new System.Drawing.Size(958, 26);
            this.toolStripMatch.TabIndex = 2;
            // 
            // tsbtnDiffExport
            // 
            this.tsbtnDiffExport.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.tsbtnDiffExport.Image = global::BookBot.Properties.Resources.excel;
            this.tsbtnDiffExport.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.tsbtnDiffExport.Name = "tsbtnDiffExport";
            this.tsbtnDiffExport.Size = new System.Drawing.Size(23, 23);
            this.tsbtnDiffExport.Text = "导出不一致数据";
            this.tsbtnDiffExport.Click += new System.EventHandler(this.tsbtnDiffExport_Click);
            // 
            // toolStripSeparator2
            // 
            this.toolStripSeparator2.Name = "toolStripSeparator2";
            this.toolStripSeparator2.Size = new System.Drawing.Size(6, 26);
            // 
            // tspbMatch
            // 
            this.tspbMatch.Alignment = System.Windows.Forms.ToolStripItemAlignment.Right;
            this.tspbMatch.Margin = new System.Windows.Forms.Padding(1, 2, 4, 1);
            this.tspbMatch.Name = "tspbMatch";
            this.tspbMatch.Size = new System.Drawing.Size(200, 23);
            // 
            // tslMatch
            // 
            this.tslMatch.Alignment = System.Windows.Forms.ToolStripItemAlignment.Right;
            this.tslMatch.Name = "tslMatch";
            this.tslMatch.Size = new System.Drawing.Size(43, 23);
            this.tslMatch.Text = "{0}/{1}";
            // 
            // bcplState
            // 
            this.bcplState.ButtonLabelBackColor = System.Drawing.Color.Black;
            this.bcplState.ButtonLabelFocusBackColor = System.Drawing.Color.Orange;
            this.bcplState.ButtonLabelForeColor = System.Drawing.Color.White;
            this.bcplState.Controls.Add(this.pnlLogs);
            this.bcplState.Controls.Add(this.lblMessage);
            this.bcplState.Editable = true;
            this.bcplState.EnphasisColor = System.Drawing.Color.Yellow;
            this.bcplState.ErrorBackColor = System.Drawing.Color.SeaShell;
            this.bcplState.GotFocusBackColor = System.Drawing.Color.LemonChiffon;
            this.bcplState.GotFocusLabelForeColor = System.Drawing.Color.Brown;
            this.bcplState.LabelFont = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.bcplState.LabelForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(102)))), ((int)(((byte)(0)))));
            this.bcplState.Location = new System.Drawing.Point(0, 104);
            this.bcplState.Name = "bcplState";
            this.bcplState.Size = new System.Drawing.Size(984, 177);
            this.bcplState.TabIndex = 0;
            this.bcplState.Text = "状态区域";
            this.bcplState.WaitCounter = 0;
            this.bcplState.Waiting = false;
            this.bcplState.WarningBackColor = System.Drawing.Color.LightYellow;
            // 
            // pnlLogs
            // 
            this.pnlLogs.Controls.Add(this.txtLogs);
            this.pnlLogs.Dock = System.Windows.Forms.DockStyle.Top;
            this.pnlLogs.Location = new System.Drawing.Point(13, 66);
            this.pnlLogs.Name = "pnlLogs";
            this.pnlLogs.Size = new System.Drawing.Size(958, 100);
            this.pnlLogs.TabIndex = 1;
            // 
            // txtLogs
            // 
            this.txtLogs.Dock = System.Windows.Forms.DockStyle.Fill;
            this.txtLogs.ForeColor = System.Drawing.Color.Blue;
            this.txtLogs.Location = new System.Drawing.Point(0, 0);
            this.txtLogs.Multiline = true;
            this.txtLogs.Name = "txtLogs";
            this.txtLogs.ScrollBars = System.Windows.Forms.ScrollBars.Both;
            this.txtLogs.Size = new System.Drawing.Size(958, 100);
            this.txtLogs.TabIndex = 0;
            // 
            // lblMessage
            // 
            this.lblMessage.AutoSize = true;
            this.lblMessage.Dock = System.Windows.Forms.DockStyle.Top;
            this.lblMessage.Font = new System.Drawing.Font("Microsoft Sans Serif", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.lblMessage.ForeColor = System.Drawing.Color.Red;
            this.lblMessage.Location = new System.Drawing.Point(18, 31);
            this.lblMessage.Margin = new System.Windows.Forms.Padding(8, 3, 8, 3);
            this.lblMessage.Name = "lblMessage";
            this.lblMessage.Size = new System.Drawing.Size(948, 29);
            this.lblMessage.TabIndex = 0;
            this.lblMessage.Text = "Message";
            // 
            // bcplSearch
            // 
            this.bcplSearch.ButtonLabelBackColor = System.Drawing.Color.Black;
            this.bcplSearch.ButtonLabelFocusBackColor = System.Drawing.Color.Orange;
            this.bcplSearch.ButtonLabelForeColor = System.Drawing.Color.White;
            this.bcplSearch.Controls.Add(this.fplInput);
            this.bcplSearch.Controls.Add(this.flpMatchButtons);
            this.bcplSearch.Editable = true;
            this.bcplSearch.EnphasisColor = System.Drawing.Color.Yellow;
            this.bcplSearch.ErrorBackColor = System.Drawing.Color.SeaShell;
            this.bcplSearch.GotFocusBackColor = System.Drawing.Color.LemonChiffon;
            this.bcplSearch.GotFocusLabelForeColor = System.Drawing.Color.Brown;
            this.bcplSearch.LabelFont = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.bcplSearch.LabelForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(102)))), ((int)(((byte)(0)))));
            this.bcplSearch.Location = new System.Drawing.Point(0, 0);
            this.bcplSearch.Name = "bcplSearch";
            this.bcplSearch.Size = new System.Drawing.Size(984, 104);
            this.bcplSearch.TabIndex = 0;
            this.bcplSearch.Text = "检索区域";
            this.bcplSearch.WaitCounter = 0;
            this.bcplSearch.Waiting = false;
            this.bcplSearch.WarningBackColor = System.Drawing.Color.LightYellow;
            // 
            // fplInput
            // 
            this.fplInput.Controls.Add(this.txtUrl);
            this.fplInput.Controls.Add(this.txtBookID);
            this.fplInput.Dock = System.Windows.Forms.DockStyle.Top;
            this.fplInput.Location = new System.Drawing.Point(13, 31);
            this.fplInput.Name = "fplInput";
            this.fplInput.Size = new System.Drawing.Size(872, 62);
            this.fplInput.TabIndex = 0;
            // 
            // flpMatchButtons
            // 
            this.flpMatchButtons.Controls.Add(this.btnStartMatch);
            this.flpMatchButtons.Controls.Add(this.btnCancleMatch);
            this.flpMatchButtons.Dock = System.Windows.Forms.DockStyle.Right;
            this.flpMatchButtons.Location = new System.Drawing.Point(891, 31);
            this.flpMatchButtons.Name = "flpMatchButtons";
            this.flpMatchButtons.Size = new System.Drawing.Size(80, 62);
            this.flpMatchButtons.TabIndex = 3;
            // 
            // btnStartMatch
            // 
            this.btnStartMatch.Location = new System.Drawing.Point(3, 3);
            this.btnStartMatch.Name = "btnStartMatch";
            this.btnStartMatch.Size = new System.Drawing.Size(75, 21);
            this.btnStartMatch.TabIndex = 1;
            this.btnStartMatch.Text = "开始抓取";
            this.btnStartMatch.UseVisualStyleBackColor = true;
            this.btnStartMatch.Click += new System.EventHandler(this.btnStartMatch_Click);
            // 
            // btnCancleMatch
            // 
            this.btnCancleMatch.Location = new System.Drawing.Point(3, 30);
            this.btnCancleMatch.Name = "btnCancleMatch";
            this.btnCancleMatch.Size = new System.Drawing.Size(75, 21);
            this.btnCancleMatch.TabIndex = 2;
            this.btnCancleMatch.Text = "取消抓取";
            this.btnCancleMatch.UseVisualStyleBackColor = true;
            this.btnCancleMatch.Click += new System.EventHandler(this.btnCancleMatch_Click);
            // 
            // tabMaster
            // 
            this.tabMaster.ButtonLabelBackColor = System.Drawing.Color.Green;
            this.tabMaster.ButtonLabelFocusBackColor = System.Drawing.Color.Orange;
            this.tabMaster.ButtonLabelForeColor = System.Drawing.Color.White;
            this.tabMaster.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tabMaster.Editable = true;
            this.tabMaster.EnphasisColor = System.Drawing.Color.Yellow;
            this.tabMaster.ErrorBackColor = System.Drawing.Color.SeaShell;
            this.tabMaster.Foldable = false;
            this.tabMaster.GotFocusBackColor = System.Drawing.Color.LemonChiffon;
            this.tabMaster.GotFocusLabelForeColor = System.Drawing.Color.Brown;
            this.tabMaster.HoverControl = null;
            this.tabMaster.IsTab = false;
            this.tabMaster.LabelFont = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.tabMaster.LabelForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(102)))), ((int)(((byte)(0)))));
            this.tabMaster.Location = new System.Drawing.Point(8, 30);
            this.tabMaster.Name = "tabMaster";
            this.tabMaster.SelectedTabControl = null;
            this.tabMaster.SelectedTabIndex = -1;
            this.tabMaster.Size = new System.Drawing.Size(934, 391);
            this.tabMaster.TabIndex = 1;
            this.tabMaster.TextVisible = false;
            this.tabMaster.WaitCounter = 0;
            this.tabMaster.Waiting = false;
            this.tabMaster.WarningBackColor = System.Drawing.Color.LightYellow;
            // 
            // tabMatch
            // 
            this.tabMatch.ButtonLabelBackColor = System.Drawing.Color.Green;
            this.tabMatch.ButtonLabelFocusBackColor = System.Drawing.Color.Orange;
            this.tabMatch.ButtonLabelForeColor = System.Drawing.Color.White;
            this.tabMatch.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tabMatch.Editable = true;
            this.tabMatch.EnphasisColor = System.Drawing.Color.Yellow;
            this.tabMatch.ErrorBackColor = System.Drawing.Color.SeaShell;
            this.tabMatch.Foldable = false;
            this.tabMatch.GotFocusBackColor = System.Drawing.Color.LemonChiffon;
            this.tabMatch.GotFocusLabelForeColor = System.Drawing.Color.Brown;
            this.tabMatch.HoverControl = null;
            this.tabMatch.IsTab = false;
            this.tabMatch.LabelFont = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.tabMatch.LabelForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(102)))), ((int)(((byte)(0)))));
            this.tabMatch.Location = new System.Drawing.Point(8, 30);
            this.tabMatch.Name = "tabMatch";
            this.tabMatch.SelectedTabControl = null;
            this.tabMatch.SelectedTabIndex = -1;
            this.tabMatch.Size = new System.Drawing.Size(948, 376);
            this.tabMatch.TabIndex = 0;
            this.tabMatch.TextVisible = false;
            this.tabMatch.WaitCounter = 0;
            this.tabMatch.Waiting = false;
            this.tabMatch.WarningBackColor = System.Drawing.Color.LightYellow;
            // 
            // txtUrl
            // 
            this.txtUrl.Location = new System.Drawing.Point(3, 3);
            this.txtUrl.Name = "txtUrl";
            this.txtUrl.Size = new System.Drawing.Size(678, 21);
            this.txtUrl.TabIndex = 0;
            this.txtUrl.Text = "http://book.douban.com/subject/";
            // 
            // txtBookID
            // 
            this.txtBookID.Location = new System.Drawing.Point(687, 3);
            this.txtBookID.Name = "txtBookID";
            this.txtBookID.Size = new System.Drawing.Size(113, 21);
            this.txtBookID.TabIndex = 1;
            this.txtBookID.Text = "1200004";
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(984, 657);
            this.Controls.Add(this.bcplData);
            this.Controls.Add(this.bcplState);
            this.Controls.Add(this.bcplSearch);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "MainForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "豆瓣图书信息爬虫";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Form1_FormClosing);
            this.Load += new System.EventHandler(this.Form1_Load);
            this.bcplData.ResumeLayout(false);
            this.bcplData.PerformLayout();
            this.dockTabData.ResumeLayout(false);
            this.dockTabData.PerformLayout();
            this.tpMatch.ResumeLayout(false);
            this.pnlMatch.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dgvMatch)).EndInit();
            this.tpDiff.ResumeLayout(false);
            this.pnlDiff.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dgvDiff)).EndInit();
            this.pnlToolScrip.ResumeLayout(false);
            this.pnlToolScrip.PerformLayout();
            this.toolStripMatch.ResumeLayout(false);
            this.toolStripMatch.PerformLayout();
            this.bcplState.ResumeLayout(false);
            this.bcplState.PerformLayout();
            this.pnlLogs.ResumeLayout(false);
            this.pnlLogs.PerformLayout();
            this.bcplSearch.ResumeLayout(false);
            this.fplInput.ResumeLayout(false);
            this.fplInput.PerformLayout();
            this.flpMatchButtons.ResumeLayout(false);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private Common.BaseControl.BlockCaptionPanel bcplData;
        private Common.BaseControl.BlockCaptionPanel bcplSearch;
        private System.Windows.Forms.FlowLayoutPanel fplInput;
        private System.Windows.Forms.FlowLayoutPanel flpMatchButtons;
        private System.Windows.Forms.Button btnStartMatch;
        private System.Windows.Forms.Button btnCancleMatch;
        private Common.BaseControl.BlockCaptionPanel bcplState;
        private System.Windows.Forms.Label lblMessage;
        private System.Windows.Forms.TextBox txtLogs;
        private Common.BaseControl.TabPanel tabMaster;
        private Common.BaseControl.TabPanel tabMatch;
        private System.Windows.Forms.DataGridView dgvMatch;
        private System.Windows.Forms.ToolStripButton tsbtnDiffExport;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
        private System.Windows.Forms.ToolStripProgressBar tspbMatch;
        private System.Windows.Forms.ToolStripLabel tslMatch;
        private System.Windows.Forms.ToolStrip toolStripMatch;
        private System.Windows.Forms.Panel pnlToolScrip;
        private System.Windows.Forms.Panel pnlLogs;
        private Common.BaseControl.TabPanel tpDiff;
        private System.Windows.Forms.DataGridView dgvDiff;
        private Common.BaseControl.TabPanel tpMatch;
        private System.Windows.Forms.Panel pnlMatch;
        private System.Windows.Forms.Panel pnlDiff;
        private Common.BaseControl.DockingTab dockTabData;
        private System.Windows.Forms.TextBox txtUrl;
        private System.Windows.Forms.TextBox txtBookID;

    }
}