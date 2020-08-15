using System;
using System.Data;
using System.Windows.Forms;
using AsynchThread;
using Common;


namespace BookBot
{
    public partial class MainForm : Form
    {
        #region Constructer
        /// <summary>
        /// 
        /// </summary>
        public MainForm()
        {
            InitializeComponent();
            this.pnlDiff.Dock = DockStyle.Fill;
            this.pnlMatch.Dock = DockStyle.Fill;
            this.dgvMatch.Dock = DockStyle.Fill;
            this.dgvDiff.Dock = DockStyle.Fill;

         

            //this.dgvMatch.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            //this.dgvDiff.SelectionMode = DataGridViewSelectionMode.FullRowSelect;

            InitMatchGridColumns(dgvMatch);
            

            _p_Task = new MatchAsyncTask();
            _p_Task.TaskProgressChanged += OnMatchTaskProcessChanged;
            _p_Task.TaskStatusChanged += OnMatchTaskStatusChanged;

        }

        #endregion

        #region Fields

        /// <summary>
        /// 比价 任务线程
        /// </summary>
        MatchAsyncTask _p_Task;

       


        private DataTable _matchTable;


        

        #endregion

        #region Properties

       

        public DataTable MatchTable
        {
            get
            {
                return (this._matchTable ?? new DataTable());
            }
            set
            {
                this._matchTable = value;
            }
        }

        #endregion

        #region Events

        #region Task Event
        /// <summary>
        /// 匹配 任务状态处理
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void OnMatchTaskStatusChanged(object sender, TaskEventArgs e)
        {
            MatchDataEntity entity = e.Result as MatchDataEntity;
            switch (e.Status)
            {
                case TaskStatus.Running:
                    
                    break;
                case TaskStatus.CancelPending:
                   
                    break;
                case TaskStatus.Stopped:
                    
                    break;
                case TaskStatus.AbortPending:

                    break;
                case TaskStatus.Aborted:
                   
                    this.btnStartMatch.Enabled = true;
                    this.btnCancleMatch.Enabled = false;
                    this.tsbtnDiffExport.Enabled = true;
                    break;
                case TaskStatus.ThrowErrorStoped:
                   
                    break;
            }
        }

        /// <summary>
        /// 匹配 任务进度处理
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void OnMatchTaskProcessChanged(object sender, TaskEventArgs e)
        {
            //不在UI线程上,异步调用
            if (InvokeRequired)
            {
                TaskEventHandler TPChanged = new TaskEventHandler(OnMatchTaskProcessChanged);

                //this.BeginInvoke(TPChanged, new object[] { sender, e });
                this.Invoke(TPChanged, new object[] { sender, e });
            }
            //在UI线程上,更新控件状态
            else
            {
                MatchDataEntity entity = e.Result as MatchDataEntity;

                this.tspbMatch.Maximum = entity.TotalCount;
                this.tspbMatch.Value = e.Progress;

               
                this.tslMatch.Text = string.Format("{0}/{1}", this.tspbMatch.Value, this.tspbMatch.Maximum);

                //DataTable dt = entity.DataTable;
                if (entity.MatchRow != null)
                {
                    this.MatchTable.ImportRow(entity.MatchRow);
                }

                

                //Application.DoEvents();
            }
        }

        #endregion

        #region Button Event
        /// <summary>
        /// 开始
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnStartMatch_Click(object sender, EventArgs e)
        {
            MatchInputEntity input = new MatchInputEntity();

            input.Url = txtUrl.Text.Trim();
            input.MatchSchema = this.MatchTable.Clone();
            input.BookID = long.Parse(txtBookID.Text.Trim());
            
            this.MatchTable.Clear();
         
            _p_Task.StartTask(input);

        }

        /// <summary>
        /// 取消比较价格
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnCancleMatch_Click(object sender, EventArgs e)
        {
            _p_Task.AbortTask();
        }

        /// <summary>
        /// 导出不一致数据
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void tsbtnDiffExport_Click(object sender, EventArgs e)
        {

            Microsoft.Office.Interop.Excel._Application app = null;
            Microsoft.Office.Interop.Excel._Workbook workbook = null;
            try
            {
                // creating Excel Application
                app = new Microsoft.Office.Interop.Excel.Application();

                // creating new WorkBook within Excel application
                workbook = app.Workbooks.Add(Type.Missing);

                // creating new Excelsheet in workbook
                Microsoft.Office.Interop.Excel._Worksheet worksheet = null;

                // see the excel sheet behind the program
                app.Visible = false;

                // get the reference of first sheet. By default its name is Sheet1.
                // store its reference to worksheet
                worksheet = workbook.Sheets["Sheet1"];
                worksheet = workbook.ActiveSheet;
                // changing the name of active sheet
                worksheet.Name = "豆瓣图书数据";

                // storing header part in Excel
                for (int i = 1; i < dgvDiff.Columns.Count + 1; i++)
                {
                    worksheet.Cells[1, i] = dgvDiff.Columns[i - 1].HeaderText;
                }

                // storing Each row and column value to excel sheet
                for (int i = 0; i < dgvDiff.Rows.Count - 1; i++)
                {
                    for (int j = 0; j < dgvDiff.Columns.Count; j++)
                    {
                        worksheet.Cells[i + 2, j + 1] = dgvDiff.Rows[i].Cells[j].Value.ToString();
                    }
                }

                string fileName = string.Empty;
                using (SaveFileDialog sfd = new SaveFileDialog())
                {
                    sfd.Filter = "Excel File |*.xlsx";
                    sfd.InitialDirectory = Environment.CurrentDirectory;
                    sfd.RestoreDirectory = true;
                    if (DialogResult.OK == sfd.ShowDialog())
                    {
                        fileName = sfd.FileName;
                    }
                }

                if (fileName != string.Empty)
                {
                    // save the application
                    workbook.SaveAs(fileName
                        , Type.Missing
                        , Type.Missing
                        , Type.Missing
                        , Type.Missing
                        , Type.Missing
                        , Microsoft.Office.Interop.Excel.XlSaveAsAccessMode.xlExclusive
                        , Type.Missing
                        , Type.Missing
                        , Type.Missing
                        , Type.Missing);

                    workbook.Close(true, fileName, false);
                }


                // Exit from the application

            }
            catch (Exception exp)
            {

            }
            finally
            {
                if (app != null)
                {
                    try
                    {
                        workbook.Close(false, Environment.CurrentDirectory + "\\" + "123.xlsx", false);
                        app.Quit();
                        workbook = null;
                        System.Runtime.InteropServices.Marshal.ReleaseComObject(app);
                        System.Runtime.InteropServices.Marshal.FinalReleaseComObject(app);
                        app = null;
                    }
                    catch
                    {

                    }
                }

            }

        }

        #endregion

        #region Form 处理
        /// <summary>
        /// 初始化控件以及早绑定DataGridView
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Form1_Load(object sender, EventArgs e)
        {
            this.btnStartMatch.Enabled = true;
            this.btnCancleMatch.Enabled = false;
            this.dockTabData.SelectedTabControl = this.tpDiff;


            DataTable dtMatch = new DataTable();
            dtMatch.Columns.Add(CommonConst.FieldName.BookID, typeof(String));
            dtMatch.Columns.Add(CommonConst.FieldName.BookName, typeof(String));
            dtMatch.Columns.Add(CommonConst.FieldName.SubTitle, typeof(String));
            dtMatch.Columns.Add(CommonConst.FieldName.Author, typeof(String));
            dtMatch.Columns.Add(CommonConst.FieldName.Translater, typeof(String));
            dtMatch.Columns.Add(CommonConst.FieldName.OriBookName, typeof(String));
            dtMatch.Columns.Add(CommonConst.FieldName.Publisher, typeof(String));
            dtMatch.Columns.Add(CommonConst.FieldName.PublishDate, typeof(String));
            dtMatch.Columns.Add(CommonConst.FieldName.Style, typeof(String));
            dtMatch.Columns.Add(CommonConst.FieldName.Price, typeof(decimal));
            dtMatch.Columns.Add(CommonConst.FieldName.Pages, typeof(Int32));
            dtMatch.Columns.Add(CommonConst.FieldName.Words, typeof(Int32));
            dtMatch.Columns.Add(CommonConst.FieldName.ISBN, typeof(String));
            dtMatch.Columns.Add(CommonConst.FieldName.Category, typeof(String));
            dtMatch.Columns.Add(CommonConst.FieldName.ContentOverview, typeof(String));
            dtMatch.Columns.Add(CommonConst.FieldName.ContentTable, typeof(String));
            dtMatch.Columns.Add(CommonConst.FieldName.AuthorOverview, typeof(String));
            dtMatch.TableName = CommonConst.TableNames.Match;

            this.MatchTable = dtMatch.Clone();

            this.dgvMatch.DataSource = this.MatchTable;

        }

        /// <summary>
        /// Form 关闭处理
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (_p_Task != null)
            {
                _p_Task.AbortTask();
            }
        }

        #endregion

        

        #endregion

        #region Methods

        #region Private

        /// <summary>
        /// 设置匹配结果Grid显示列样式
        /// </summary>
        private void InitMatchGridColumns(DataGridView dgv)
        {

            
            DataGridViewTextBoxColumn BookID = NewColumn(CommonConst.FieldName.BookID, CommonConst.FieldName.BookID);
            DataGridViewTextBoxColumn BookName = NewColumn(CommonConst.FieldName.BookName, CommonConst.FieldName.BookName);
            DataGridViewTextBoxColumn SubTitle = NewColumn(CommonConst.FieldName.SubTitle, CommonConst.FieldName.SubTitle);
            DataGridViewTextBoxColumn Author = NewColumn(CommonConst.FieldName.Author, CommonConst.FieldName.Author);
            DataGridViewTextBoxColumn Translater = NewColumn(CommonConst.FieldName.Translater, CommonConst.FieldName.Translater);
            DataGridViewTextBoxColumn OriBookName = NewColumn(CommonConst.FieldName.OriBookName, CommonConst.FieldName.OriBookName);
            DataGridViewTextBoxColumn Publisher = NewColumn(CommonConst.FieldName.Publisher, CommonConst.FieldName.Publisher);
            DataGridViewTextBoxColumn PublishDate = NewColumn(CommonConst.FieldName.PublishDate, CommonConst.FieldName.PublishDate);
            DataGridViewTextBoxColumn Pages = NewColumn(CommonConst.FieldName.Pages, CommonConst.FieldName.Pages);
            DataGridViewTextBoxColumn Words = NewColumn(CommonConst.FieldName.Words, CommonConst.FieldName.Words);
            DataGridViewTextBoxColumn Style = NewColumn(CommonConst.FieldName.Style, CommonConst.FieldName.Style);
            DataGridViewTextBoxColumn Price = NewColumn(CommonConst.FieldName.Price, CommonConst.FieldName.Price);
            DataGridViewTextBoxColumn ISBN = NewColumn(CommonConst.FieldName.ISBN, CommonConst.FieldName.ISBN);
            DataGridViewTextBoxColumn Category = NewColumn(CommonConst.FieldName.Category, CommonConst.FieldName.Category);
            DataGridViewTextBoxColumn ContentOverview = NewColumn(CommonConst.FieldName.ContentOverview, CommonConst.FieldName.ContentOverview);
            DataGridViewTextBoxColumn ContentTable = NewColumn(CommonConst.FieldName.ContentTable, CommonConst.FieldName.ContentTable);
            DataGridViewTextBoxColumn AuthorOverview = NewColumn(CommonConst.FieldName.AuthorOverview, CommonConst.FieldName.AuthorOverview);


            

            dgv.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            BookID,
            BookName,
            SubTitle,
            Author,
            Translater,
            OriBookName,
            Publisher,
            PublishDate,
            Pages,
            Words,
            Style,
            Price,
            ISBN,
            Category,
            ContentOverview,
            ContentTable,
            AuthorOverview

            });
        }

        private static DataGridViewTextBoxColumn NewColumn(string FieldName, string HeaderText)
        {
            DataGridViewTextBoxColumn column = new DataGridViewTextBoxColumn();
            column.DataPropertyName = FieldName;
            column.HeaderText = HeaderText;
            column.MinimumWidth = 80;
            column.Name = FieldName;
            column.ReadOnly = true;
            column.Width = 80;
            return column;
        }

        #endregion



        #endregion
    }
}
