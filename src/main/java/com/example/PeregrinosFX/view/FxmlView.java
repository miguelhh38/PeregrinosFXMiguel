package com.example.PeregrinosFX.view;

import java.util.ResourceBundle;

public enum FxmlView {
    MENUPRINCIPAL {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("menuprincipal.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/MenuPrincipal.fxml";
        }
    },
    LOGIN {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("login.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Login.fxml";
        }
    },

    REGISTRO {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("registro.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Registro.fxml";
        }
    },

    USUARIOCREADO {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("usuariocreado.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/UsuarioCreado.fxml";
        }
    },

    MENUPEREGRINO {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("menuperegrino.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/MenuPeregrino.fxml";
        }
    },

    MENUADMINPARADA {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("menuadminparada.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/MenuAdminParada.fxml";
        }
    },

    MENUADMINGENERAL {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("menuadmingeneral.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/MenuAdminGeneral.fxml";
        }
    },

    DATOSPARADA {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("datosparada.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/DatosParada.fxml";
        }
    },

    EXPORTARCARNET {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("exportarcarnet.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/ExportarCarnet.fxml";
        }
    },

    ALOJARSE {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("alojarse.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Alojarse.fxml";
        }
    },

    MENUSERVCIOS {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("menuservicios.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/MenuServicios.fxml";
        }
    },

    NUEVOSERVICIO {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("nuevoservicio.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/NuevoServicio.fxml";
        }
    },

    EDITARSERVICIO {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("editarservicio.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/EditarServicio.fxml";
        }
    },

    ENVIOCASA {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("enviocasa.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/envioCasa.fxml";
        }
    },

    VERENVIOS {

        @Override
        public String getTitle() {
            return getStringFromResourceBundle("verenvios.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/verEnvios.fxml";
        }
    },

    VERCARNETS {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("vercarnets.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/verCarnets.fxml";
        }

    };




    public abstract String getTitle();
    public abstract String getFxmlFile();

    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }


}
