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
using Ekaterina_Stroevitch_Krasnova_Práctica2.UserControls;

namespace Ekaterina_Stroevitch_Krasnova_Práctica2
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private static List<Empleado> empleadoList;

        public static List<Empleado> EmpleadoList { 
            get { return empleadoList; }
            set { empleadoList = value; }
        }
        private static Empleado empleadoActual;

        public static Empleado EmpleadoActual
        {
            get { return empleadoActual; }
            set
            {
                empleadoActual = value;
            }
        }

        public MainWindow()
        {
            InitializeComponent();
            EmpleadoList = new List<Empleado>();
            List<string> resps = new List<string>();
            resps.Add("Captación y mantenimiento de sponsors");
            resps.Add("Relación con usuarios");
            EmpleadoList.Add(new Empleado()
            {
                Nombre = "Juan",
                Apellidos = "Palomo",
                Correo = "jpalomo@gmail.com",
                Contrasenia = "1234",
                Departamento = "Servicios compartidos",
                Puesto = "Jefe",
                Posicion = "Empleado",
                Responsabilidades = resps,
                FechaContratacion = "06/04/2022",
                Ciudad = "Madrid"
            });


        }
        public static void CrearItem_Click(object sender, RoutedEventArgs e)
        {
            CrearEditar.Editar = false;
            empleadoActual = null;
            CrearEditar ce = new CrearEditar();

            MainWindow window = Application.Current.MainWindow as MainWindow;
            window.Content = ce;
        }
        public static void ModificarItem_Click(object sender, RoutedEventArgs e)
        {
            CrearEditar.Editar = true;
            CrearEditar ce = new CrearEditar();


            MainWindow window = Application.Current.MainWindow as MainWindow;
            window.Content = ce;
        }

        public static void VerEmpleados_Click(object sender, RoutedEventArgs e)
        {
            VisualizacionEmpleados ve = new VisualizacionEmpleados();
            MainWindow window = Application.Current.MainWindow as MainWindow;
            window.Content = ve;
        }

        public static void VolverInicio_Click(object sender, RoutedEventArgs e)
        {
            Inicio inicio = new Inicio();
            MainWindow window = Application.Current.MainWindow as MainWindow;
            window.Content = inicio;
        }

    }
}
