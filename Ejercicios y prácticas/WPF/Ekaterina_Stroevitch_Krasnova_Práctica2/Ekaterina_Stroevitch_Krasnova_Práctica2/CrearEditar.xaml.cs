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
using System.Text.RegularExpressions;

namespace Ekaterina_Stroevitch_Krasnova_Práctica2
{
    /// <summary>
    /// Lógica de interacción para CrearEditar.xaml
    /// </summary>
    public partial class CrearEditar : Page
    {
        public CrearEditar()
        {
            InitializeComponent();
        }

        private void Button_Crear(object sender, RoutedEventArgs e)
        {
            ComboBoxItem cbiD = (ComboBoxItem)ComboDepartamento.SelectedItem;
            ComboBoxItem cbiC = (ComboBoxItem)ComboCiudad.SelectedItem;
            //System.Collections.IList items = (System.Collections.IList)ListaResponsabilidades.SelectedItems;
            List<string> resps = new List<string>();
            foreach (ListBoxItem item in ListaResponsabilidades.SelectedItems)
            {
                resps.Add(item.Content.ToString());
            }
            

            Empleado = new Empleado() { Nombre = Nombre.Text, Apellidos = Apellidos.Text,
                Correo = Correo.Text, Responsabilidades = resps, Contrasenia = Contrasenia.Password.ToString(), 
                Departamento = cbiD.Content.ToString(), Posicion = this.Pos, Puesto = Puesto.Text, 
                FechaContratacion = Date.SelectedDate.Value.ToString("dd/MM/yyyy"), Ciudad = cbiC.Content.ToString()};
            MainWindow.EmpleadoList.Add(Empleado);

            MessageBox.Show("Se ha creado el empleado " + Empleado.Nombre + " " + Empleado.Apellidos);


        }
        internal string Pos;
        public Empleado Empleado;

        private void ValidarDatos(string nombre, string apellidos, string correo, List<string> responsabilidades,
            string contrasenia, string departamento, string posicion, string puesto, string fecha, string ciudad)
        {
            string joined = String.Join("\n", responsabilidades);
            Dictionary<string, string> dic = new Dictionary<string, string>();
            dic["nombre"] = nombre;
            dic["apellidos"] = apellidos;
            dic["correo"] = correo;
            dic["responsabilidades"] = joined;
            dic["contrasenia"] = contrasenia;
            dic["departamento"] = departamento;
            dic["posicion"] = posicion;
            dic["puesto"] = puesto;
            dic["fecha"] = fecha;
            dic["ciudad"] = ciudad;

            List<string> errores = new List<string>();
            foreach (KeyValuePair<string, string> entry in dic)
            {
                // do something with entry.Value or entry.Key
                if(entry.Value == "")
                {
                    errores.Add("El campo " + entry.Key + " no puede estar vacío");
                }
                else
                {
                    if(entry.Key == "contrasenia")
                    {
                        Regex patron = new Regex(@"^(?=.{6,12}$)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).*$");
                        if (!patron.IsMatch(entry.Value))
                        {
                            errores.Add("La contraseña no es válida");
                        }
                    }
                    else if (entry.Key == "correo")
                    {
                        Regex patron = new Regex(@"[a-z]+@[a-z]+\\.[a-z]+");
                        if (!patron.IsMatch(entry.Value))
                        {
                            errores.Add("El correo no es válido");
                        }
                    }
                    else if (entry.Key == "fecha")
                    {
                        DateTime temp;
                        if (!DateTime.TryParse(fecha, out temp))
                        {
                            errores.Add("La fecha no es válida. Usa el formato dd/mm/yyyy");
                        }
                    }
                }
            }
            if(errores.Count > 0)
            {
                MessageBox.Show(String.Join("\n", errores));
            }
        }

        private void RadioEmpleado_Click(object sender, RoutedEventArgs e)
        {
            Pos = "Empleado";
        }

        private void RadioAdmin_Click(object sender, RoutedEventArgs e)
        {
            Pos = "Admin";
        }

        private void RadioDirector_Click(object sender, RoutedEventArgs e)
        {
            Pos = "Director";
        }
    }
}
