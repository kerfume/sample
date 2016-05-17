using System.Windows.Forms;

namespace TableCreater
{
    partial class Form1
    {
        /// <summary>
        /// 必要なデザイナー変数です。
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 使用中のリソースをすべてクリーンアップします。
        /// </summary>
        /// <param name="disposing">マネージ リソースを破棄する場合は true を指定し、その他の場合は false を指定します。</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows フォーム デザイナーで生成されたコード

        /// <summary>
        /// デザイナー サポートに必要なメソッドです。このメソッドの内容を
        /// コード エディターで変更しないでください。
        /// </summary>
        private void InitializeComponent()
        {
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.makesqlb = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.tname = new System.Windows.Forms.TextBox();
            this.comit = new System.Windows.Forms.Button();
            this.cname = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Type = new System.Windows.Forms.DataGridViewComboBoxColumn();
            this.nums = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.NotNull = new System.Windows.Forms.DataGridViewCheckBoxColumn();
            this.UNIQUE = new System.Windows.Forms.DataGridViewCheckBoxColumn();
            this.Primary = new System.Windows.Forms.DataGridViewCheckBoxColumn();
            this.UNSIGNED = new System.Windows.Forms.DataGridViewCheckBoxColumn();
            this.ZEROFILL = new System.Windows.Forms.DataGridViewCheckBoxColumn();
            this.DEFULT = new System.Windows.Forms.DataGridViewTextBoxColumn();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridView1
            // 
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.cname,
            this.Type,
            this.nums,
            this.NotNull,
            this.UNIQUE,
            this.Primary,
            this.UNSIGNED,
            this.ZEROFILL,
            this.DEFULT});
            this.dataGridView1.Location = new System.Drawing.Point(12, 51);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.RowTemplate.Height = 21;
            this.dataGridView1.Size = new System.Drawing.Size(790, 187);
            this.dataGridView1.TabIndex = 0;
            this.dataGridView1.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridView1_CellContentClick);
            // 
            // textBox1
            // 
            this.textBox1.Location = new System.Drawing.Point(12, 306);
            this.textBox1.Multiline = true;
            this.textBox1.Name = "textBox1";
            this.textBox1.ScrollBars = System.Windows.Forms.ScrollBars.Both;
            this.textBox1.Size = new System.Drawing.Size(790, 174);
            this.textBox1.TabIndex = 1;
            this.textBox1.WordWrap = false;
            // 
            // makesqlb
            // 
            this.makesqlb.Location = new System.Drawing.Point(12, 255);
            this.makesqlb.Name = "makesqlb";
            this.makesqlb.Size = new System.Drawing.Size(116, 31);
            this.makesqlb.TabIndex = 2;
            this.makesqlb.Text = "SQL生成";
            this.makesqlb.UseVisualStyleBackColor = true;
            this.makesqlb.Click += new System.EventHandler(this.makeSqlb_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(14, 20);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(73, 12);
            this.label1.TabIndex = 3;
            this.label1.Text = "TABLENAME";
            // 
            // tname
            // 
            this.tname.Location = new System.Drawing.Point(93, 17);
            this.tname.Name = "tname";
            this.tname.Size = new System.Drawing.Size(132, 19);
            this.tname.TabIndex = 4;
            // 
            // comit
            // 
            this.comit.Location = new System.Drawing.Point(691, 487);
            this.comit.Name = "comit";
            this.comit.Size = new System.Drawing.Size(111, 29);
            this.comit.TabIndex = 5;
            this.comit.Text = "登録";
            this.comit.UseVisualStyleBackColor = true;
            this.comit.Click += new System.EventHandler(this.comit_Click);
            // 
            // cname
            // 
            this.cname.HeaderText = "Name";
            this.cname.Name = "cname";
            // 
            // Type
            // 
            this.Type.HeaderText = "Type";
            this.Type.Items.AddRange(new object[] {
            "INT",
            "TINYINT",
            "VARCHAR",
            "TEXT",
            "DATETIME"});
            this.Type.Name = "Type";
            // 
            // nums
            // 
            this.nums.HeaderText = "桁数";
            this.nums.MaxInputLength = 3;
            this.nums.Name = "nums";
            this.nums.Width = 80;
            // 
            // NotNull
            // 
            this.NotNull.HeaderText = "NotNull";
            this.NotNull.Name = "NotNull";
            this.NotNull.Resizable = System.Windows.Forms.DataGridViewTriState.True;
            this.NotNull.Width = 70;
            // 
            // UNIQUE
            // 
            this.UNIQUE.HeaderText = "UNIQUE";
            this.UNIQUE.Name = "UNIQUE";
            this.UNIQUE.Width = 70;
            // 
            // Primary
            // 
            this.Primary.HeaderText = "Primary";
            this.Primary.Name = "Primary";
            this.Primary.Width = 70;
            // 
            // UNSIGNED
            // 
            this.UNSIGNED.HeaderText = "UNSIGNED";
            this.UNSIGNED.Name = "UNSIGNED";
            this.UNSIGNED.Resizable = System.Windows.Forms.DataGridViewTriState.True;
            this.UNSIGNED.SortMode = System.Windows.Forms.DataGridViewColumnSortMode.Automatic;
            this.UNSIGNED.Width = 70;
            // 
            // ZEROFILL
            // 
            this.ZEROFILL.HeaderText = "ZEROFILL";
            this.ZEROFILL.Name = "ZEROFILL";
            this.ZEROFILL.Width = 70;
            // 
            // DEFULT
            // 
            this.DEFULT.HeaderText = "DEFULT";
            this.DEFULT.Name = "DEFULT";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(814, 528);
            this.Controls.Add(this.comit);
            this.Controls.Add(this.tname);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.makesqlb);
            this.Controls.Add(this.textBox1);
            this.Controls.Add(this.dataGridView1);
            this.Name = "Form1";
            this.Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridView1;
        private System.Windows.Forms.TextBox textBox1;
        private Button makesqlb;
        private Label label1;
        private TextBox tname;
        private Button comit;
        private DataGridViewTextBoxColumn cname;
        private DataGridViewComboBoxColumn Type;
        private DataGridViewTextBoxColumn nums;
        private DataGridViewCheckBoxColumn NotNull;
        private DataGridViewCheckBoxColumn UNIQUE;
        private DataGridViewCheckBoxColumn Primary;
        private DataGridViewCheckBoxColumn UNSIGNED;
        private DataGridViewCheckBoxColumn ZEROFILL;
        private DataGridViewTextBoxColumn DEFULT;
    }
}

