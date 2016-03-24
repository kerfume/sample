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

namespace WindowsFormsApplication1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }
        void button1_Click(object sender, EventArgs e)
        {
            this.textBox1.Text = getUsernum();
            /*if ((bool)this.dataGridView1.Rows[0].Cells[1].Value)
            {
                this.textBox1.Text = "trueやわ。";
            } 値取得テスト*/
        }

        void button2_Click(object sender, EventArgs e)
        {
            if (!this.textBox2.Text.Equals("")){
                this.dataGridView1.Rows.Add(this.textBox2.Text);

            }
            else {
            this.textBox1.Text = "ふげ";
            }
        }
        void button3_Click(object sender, EventArgs e)
        {
            int rownum = dataGridView1.Rows.Count;


            if ( rownum != 0)
                {
                    if (this.dataGridView1.Rows[0].Cells[1].Value != null &&
                        (bool)this.dataGridView1.Rows[0].Cells[1].Value == true)
                    {
                        this.textBox1.Text = rownum + "行でtrueやわ。";
                    }
     
            }
            else {
                this.textBox1.Text = "ふげ";
            }
        }

        String getUsernum()
        {
            String result = "";
            using (var con = new MySqlConnection("server=127.0.0.1;user=mmauser;password=mmapass;database=mma;")) //serverはlocalhostでも可
            {
                con.Open();
                var command = new MySqlCommand("select count(*) from tb_mst_user;", con);
                var reader = command.ExecuteReader();
                while (reader.Read())
                {
                    result = reader.GetString(0);
                }

                return result;
            }
        }
    }

}
