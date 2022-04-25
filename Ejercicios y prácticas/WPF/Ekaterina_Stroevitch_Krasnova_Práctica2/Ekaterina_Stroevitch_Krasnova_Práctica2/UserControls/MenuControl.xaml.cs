using System;
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

namespace Ekaterina_Stroevitch_Krasnova_Práctica2.UserControls
{
    /// <summary>
    /// Lógica de interacción para MenuControl.xaml
    /// </summary>
    public partial class MenuControl : UserControl
    {
        public MenuControl()
        {
            InitializeComponent();
        }

        private void CrearItem_Click(object sender, RoutedEventArgs e)
        {
            CrearEditar.Editar = false;
            CrearEditar ce = new CrearEditar();
            
            MainWindow window = Application.Current.MainWindow as MainWindow;
            window.Content = ce;
        }

        private void VerEmpleados_Click(object sender, RoutedEventArgs e)
        {
            VisualizacionEmpleados ve = new VisualizacionEmpleados();
            MainWindow window = Application.Current.MainWindow as MainWindow;
            window.Content = ve;
        }

        private void VolverInicio_Click(object sender, RoutedEventArgs e)
        {
            Inicio inicio = new Inicio();
            MainWindow window = Application.Current.MainWindow as MainWindow;
            window.Content = inicio;
        }
    }
}
