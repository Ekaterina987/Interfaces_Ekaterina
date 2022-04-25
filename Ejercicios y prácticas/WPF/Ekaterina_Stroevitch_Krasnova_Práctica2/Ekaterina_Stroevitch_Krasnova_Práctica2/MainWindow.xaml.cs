using System;
using System.Diagnostics;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Ekaterina_Stroevitch_Krasnova_Práctica2
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
            
        }


        private void CrearItem_Click(object sender, RoutedEventArgs e)
        {
            CrearEditar ce = new CrearEditar();
            this.Content = ce;
        }

        private void VerEmpleados_Click(object sender, RoutedEventArgs e)
        {
            VisualizacionEmpleados ve = new VisualizacionEmpleados();
            this.Content = ve;
        }
    }
}
