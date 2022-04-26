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
using System.ComponentModel;
using System.Runtime.CompilerServices;
using System.Collections.ObjectModel;

namespace Ekaterina_Stroevitch_Krasnova_Práctica2
{
    /// <summary>
    /// Lógica de interacción para CrearEditar.xaml
    /// </summary>
    public partial class CrearEditar : Page
    {
        private static MainWindow ventanaPrincipal;
        public static MainWindow VentanaPrincipal
        {
            get { return ventanaPrincipal; }
            set { ventanaPrincipal = value; }
        }

        private static bool editar;

        public static bool Editar
        {
            get
            {
                return editar;
            }
            set
            {
                editar = value;
            }
        }

        private string textoBotonCrear;

        public string TextoBotonCrear
        {
            get { return textoBotonCrear; }
            set
            {
                textoBotonCrear = value;
            }
        }

        private string textoTitulo;

        public string TextoTitulo
        {
            get { return textoTitulo; }
            set
            {
                textoTitulo = value;
            }
        }

        public CrearEditar()
        {
            InitializeComponent();
            InicializarValores();
            VentanaPrincipal.IsEmpleadoSelected = false;
            this.DataContext = this;
        }

        private void InicializarValores()
        {
            if (editar)
            {
                TextoTitulo = "Modificar Empleado";
                TextoBotonCrear = "Guardar";
                if (VentanaPrincipal.EmpleadoActual != null)
                {
                    Nombre.Text = VentanaPrincipal.EmpleadoActual.Nombre;
                    Apellidos.Text = VentanaPrincipal.EmpleadoActual.Apellidos;
                    Correo.Text = VentanaPrincipal.EmpleadoActual.Correo;
                    Puesto.Text = VentanaPrincipal.EmpleadoActual.Puesto;
                    for(int i = 0; i < ListaResponsabilidades.Items.Count; i++)
                    {
                        for(int j = 0; j < VentanaPrincipal.EmpleadoActual.Responsabilidades.Count; j++)
                        {
                            ListBoxItem lbi = (ListBoxItem)ListaResponsabilidades.Items.GetItemAt(i);
                            if (VentanaPrincipal.EmpleadoActual.Responsabilidades[j] == lbi.Content.ToString())
                            {
                                ListaResponsabilidades.SelectedItems.Add(ListaResponsabilidades.Items.GetItemAt(i));
                            }
                        }
                        
                    }
                    Contrasenia.Password = VentanaPrincipal.EmpleadoActual.Contrasenia;
                    ComboDepartamento.SelectedValue = VentanaPrincipal.EmpleadoActual.Departamento;
                    Date.SelectedDate = DateTime.Parse(VentanaPrincipal.EmpleadoActual.FechaContratacion);
                    switch (VentanaPrincipal.EmpleadoActual.Posicion)
                    {
                        case "Empleado":
                            RadioEmpleado.IsChecked = true;
                            break;
                        case "Administrador":
                            RadioAdmin.IsChecked = true;
                            break;
                        case "Director":
                            RadioDirector.IsChecked = true;
                            break;
                    }
                    ComboCiudad.SelectedValue = VentanaPrincipal.EmpleadoActual.Ciudad;
                }
            }
            else if(!editar)
            {
                TextoTitulo = "Crear Empleado";
                TextoBotonCrear = "Crear";
            }
        }
        internal string Pos;

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
            string comboD = "";
            if (ComboDepartamento.SelectedIndex != -1)
            {
                comboD = cbiD.Content.ToString();
            }
            string comboC = "";
            if (ComboCiudad.SelectedIndex != -1)
            {
                comboC = cbiC.Content.ToString();
            }
            string fechaC = "";
            if (Date.SelectedDate != null)
            {
                fechaC = Date.SelectedDate.Value.ToString("dd/MM/yyyy");
            }
            if (RadioEmpleado.IsChecked == true)
            {
                this.Pos = "Empleado";
            }else if(RadioAdmin.IsChecked == true)
            {
                this.Pos = "Administrador";
            }
            else if (RadioDirector.IsChecked == true)
            {
                this.Pos = "Director";
            }
            if (editar)
            {
                ValidarDatos(Nombre.Text,
                    Apellidos.Text,
                    Correo.Text,
                    resps,
                    Contrasenia.Password.ToString(),
                    comboD,
                    this.Pos,
                    Puesto.Text,
                    fechaC,
                    comboC,
                    VentanaPrincipal.EmpleadoActual);
            }
            else
            {
                ValidarDatos(Nombre.Text,
                    Apellidos.Text,
                    Correo.Text,
                    resps,
                    Contrasenia.Password.ToString(),
                    comboD,
                    this.Pos,
                    Puesto.Text,
                    fechaC,
                    comboC,
                    new Empleado());
            }

        }
        

        private void ValidarDatos(string nombre, string apellidos, string correo, List<string> responsabilidades,
            string contrasenia, string departamento, string posicion, string puesto, string fecha, string ciudad, Empleado empleado)
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
                        Regex patron = new Regex(@"[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$");
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
                MessageBox.Show(String.Join("\n", errores), "Error", MessageBoxButton.OK, MessageBoxImage.Error);
            }
            else
            {
                ObservableCollection<Empleado> Collection = VentanaPrincipal.EmpleadosList;
                if (Collection.Contains(empleado))
                {

                    MessageBoxResult messageBoxResult = System.Windows.MessageBox.Show("¿Quieres modificar a " + empleado.Nombre + " " + empleado.Apellidos + "?", "Confirmar modificación", System.Windows.MessageBoxButton.YesNo);
                    if (messageBoxResult == MessageBoxResult.Yes)
                    {
                        var index = Collection.IndexOf(empleado);
                        Empleado anterior = empleado.Clone();
                        Collection[index].Nombre = nombre;
                        Collection[index].Apellidos = apellidos;
                        Collection[index].Correo = correo;
                        Collection[index].Responsabilidades = responsabilidades;
                        Collection[index].Contrasenia = contrasenia;
                        Collection[index].Departamento = departamento;
                        Collection[index].Posicion = posicion;
                        Collection[index].Puesto = puesto;
                        Collection[index].FechaContratacion = fecha;
                        Collection[index].Ciudad = ciudad;

                        MessageBox.Show("Se han modificado los datos del empleado " + anterior.Nombre + " " + anterior.Apellidos, "Éxito");
                    }
                }
                else
                {
                    empleado = new Empleado()
                    {
                        Nombre = nombre,
                        Apellidos = apellidos,
                        Correo = correo,
                        Responsabilidades = responsabilidades,
                        Contrasenia = contrasenia,
                        Departamento = departamento,
                        Posicion = posicion,
                        Puesto = puesto,
                        FechaContratacion = fecha,
                        Ciudad = ciudad
                    };
                    Collection.Add(empleado);
                    MessageBox.Show("Se ha creado el empleado " + empleado.Nombre + " " + empleado.Apellidos, "Éxito");
                }


                VentanaPrincipal.EmpleadosList = Collection;

            }
        }

        private void Button_Descartar(object sender, RoutedEventArgs e)
        {
            MessageBoxResult messageBoxResult = System.Windows.MessageBox.Show("¿Quieres descartar los cambios?", "Confirmar descartado", System.Windows.MessageBoxButton.YesNo);
            if (messageBoxResult == MessageBoxResult.Yes)
            {
                VentanaPrincipal.VerEmpleados_Click(sender, e);
            }
        }
    }
}
