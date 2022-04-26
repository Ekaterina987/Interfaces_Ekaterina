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
using System.Collections.ObjectModel;

namespace Ekaterina_Stroevitch_Krasnova_Práctica2
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private Empleado empleadoActual;

        public Empleado EmpleadoActual
        {
            get { return empleadoActual; }
            set
            {
                empleadoActual = value;
            }
        }
        public Boolean IsEmpleadoSelected
        {
            get { return (Boolean)GetValue(IsEmpleadoSelectedProperty); }
            set { SetValue(IsEmpleadoSelectedProperty, value); }
        }

        public static readonly DependencyProperty IsEmpleadoSelectedProperty =
            DependencyProperty.Register("IsEmpleadoSelected", typeof(Boolean), typeof(MainWindow), new PropertyMetadata(default(Boolean)));

        public ObservableCollection<Empleado> EmpleadosList { get; set; }

        public MainWindow()
        {
            InitializeComponent();
            IsEmpleadoSelected = false;

            
            MenuControl.VentanaPrincipal = this;
            Inicio.VentanaPrincipal = this;
            Inicio inicio = new Inicio();
            MainWindow window = this;
            window.Content = inicio;
            
            VisualizacionEmpleados.VentanaPrincipal = this;
            CrearEditar.VentanaPrincipal = this;
            

            this.DataContext = this;

            EmpleadosList = new ObservableCollection<Empleado>();

            List<string> resps = new List<string>();

            resps.Add("Captación y mantenimiento de sponsors");
            resps.Add("Relación con usuarios");
            EmpleadosList.Add(new Empleado()
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
        public void CrearItem_Click(object sender, RoutedEventArgs e)
        {
            CrearEditar.Editar = false;
            empleadoActual = null;
            CrearEditar ce = new CrearEditar();

            MainWindow window = Application.Current.MainWindow as MainWindow;
            window.Content = ce;
        }
        public void ModificarItem_Click(object sender, RoutedEventArgs e)
        {
            CrearEditar.Editar = true;
            CrearEditar ce = new CrearEditar();


            MainWindow window = Application.Current.MainWindow as MainWindow;
            window.Content = ce;
        }

        public void VerEmpleados_Click(object sender, RoutedEventArgs e)
        {
            VisualizacionEmpleados ve = new VisualizacionEmpleados();
            MainWindow window = Application.Current.MainWindow as MainWindow;
            window.Content = ve;
        }

        public void VolverInicio_Click(object sender, RoutedEventArgs e)
        {
            Inicio inicio = new Inicio();
            MainWindow window = Application.Current.MainWindow as MainWindow;
            window.Content = inicio;
        }
        public void BorrarItem_Click(object sender, RoutedEventArgs e)
        {
            if (EmpleadosList.Contains(empleadoActual))
            {
                MessageBoxResult messageBoxResult = System.Windows.MessageBox.Show("¿Quieres borrar al empleado " + empleadoActual.Nombre + " " + empleadoActual.Apellidos + "?", "Confirmar borrado", System.Windows.MessageBoxButton.YesNo);
                if (messageBoxResult == MessageBoxResult.Yes)
                {
                    EmpleadosList.Remove(empleadoActual);
                    IsEmpleadoSelected = false;
                }
                
            }
        }

    }
}
