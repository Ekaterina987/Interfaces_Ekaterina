using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ekaterina_Stroevitch_Krasnova_Práctica2
{
    public class Empleado
    {
        public string Nombre { get; set; }
        public string Apellidos { get; set; }
        public string Correo { get; set; }
        public List<string> Responsabilidades { get; set; }
        public string Contrasenia { get; set; }
        public string Departamento { get; set; }
        public string Posicion { get; set; }
        public string Puesto { get; set; }
        public string FechaContratacion { get; set; }
        public string Ciudad { get; set; }

        public Empleado Clone()
        {
            return (Empleado)this.MemberwiseClone();
        }
    }
}
