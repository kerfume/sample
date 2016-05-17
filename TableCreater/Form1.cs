using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

using MySql.Data.MySqlClient;

namespace TableCreater
{
    public partial class Form1 : Form
    {
        StringBuilder sql;
        List<String> pk;
        
        public Form1()
        {
            InitializeComponent();
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }
        void makeSqlb_Click(object sender, EventArgs e)
        {
            sql = new StringBuilder("CREATE TABLE ");
            sql.Append(tname.Text + "(");
            pk = new List<String>();

            for (int i=0;i < dataGridView1.Rows.Count-1; i++)
            {
                if(i != 0)
                    sql.Append(",");
                string tname = (string)dataGridView1.Rows[i].Cells[0].Value;
                string type = (string)dataGridView1.Rows[i].Cells[1].Value;
                int tnum = int.Parse(dataGridView1.Rows[i].Cells[2].Value != null ? (string)dataGridView1.Rows[i].Cells[2].Value:"0");
                bool nn = (bool)(dataGridView1.Rows[i].Cells[3].Value != null ? dataGridView1.Rows[i].Cells[3].Value : false);
                bool uni = (bool)(dataGridView1.Rows[i].Cells[4].Value != null ? dataGridView1.Rows[i].Cells[4].Value : false);
                bool usi = (bool)(dataGridView1.Rows[i].Cells[6].Value != null ? dataGridView1.Rows[i].Cells[6].Value : false);
                bool zer = (bool)(dataGridView1.Rows[i].Cells[7].Value != null ? dataGridView1.Rows[i].Cells[7].Value : false);
                string defu = (string)dataGridView1.Rows[i].Cells[8].Value;

                //主キーlistの生成
                if ((bool)(dataGridView1.Rows[i].Cells[5].Value != null ? dataGridView1.Rows[i].Cells[5].Value : false))
                {
                    pk.Add(tname);
                }
                plusColumn(tname,type,tnum,nn,uni,usi,zer,defu);
            }
            //主キー設定メソッド作成
            if (pk.Count != 0)
                plusPrimary(pk);

            sql.Append(");");
            textBox1.Text = sql.ToString();
        }

        void comit_Click(object sender, EventArgs e)
        {
            int nRet;
            using (var con = new MySqlConnection("server=127.0.0.1;user=root;password=tinyfox7;database=mma;")) //serverはlocalhostでも可
            {
                con.Open();
                var command = new MySqlCommand(textBox1.Text, con);
                nRet = command.ExecuteNonQuery();
                textBox1.Text = textBox1.Text + "　リザルト:" + nRet;
            }
        }

       /* private void DataGridView1_CellEnter(object sender,DataGridViewCellEventArgs e)
        {
            DataGridView dgv = (DataGridView)sender;

            if (dgv.Columns[e.ColumnIndex].Name == "Type" &&
               dgv.Columns[e.ColumnIndex] is DataGridViewComboBoxColumn)
            {
                SendKeys.Send("{F4}");
            }
        }*/

        private void plusColumn(string tname,string type,int tnum, bool nn, bool uni,bool usi,bool zer,string defu)
        {
            sql.Append(tname + " " + type);
            if (tnum != 0)
                sql.Append("(" + tnum +")");
            if (usi)
                sql.Append(" UNSIGNED");
            if (zer)
                sql.Append(" ZEROFILL");
            if (nn)
                sql.Append(" NOT NULL");
            if (uni)
                sql.Append(" UNIQUE");
            if (defu != null && defu != "")
                sql.Append(" DEFAULT " + defu);
        }

        private void plusPrimary(List<String> pk)
        {
            sql.Append(",PRIMARY KEY(");
            bool first = false;
            foreach (string s in pk)
            {
                if(first == false)
                    first = true;
                else
                    sql.Append(",");
                sql.Append(s);
            }
            sql.Append(")");
        }

    }
}
