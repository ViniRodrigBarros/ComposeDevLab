# ComposeDevLab

Um laboratório de estudos e experimentação com **Kotlin** e **Jetpack Compose**, desenvolvido para explorar, praticar e consolidar conhecimentos no desenvolvimento Android moderno.

---

## Sobre o Projeto

O **ComposeDevLab** é um aplicativo mobile Android que funciona como um ambiente de experimentação contínua. A ideia central é que novas funcionalidades sejam implementadas de forma **incremental e independente**, sem que uma dependa da outra, permitindo explorar diferentes conceitos, componentes e abordagens do ecossistema Android moderno.

A aplicação possui uma tela inicial (**Home**) que centraliza o acesso a cada funcionalidade implementada, funcionando como um catálogo navegável de experimentos.

---

## Tecnologias Utilizadas

| Tecnologia | Versão |
|---|---|
| Kotlin | 2.0.21 |
| Jetpack Compose BOM | 2024.11.00 |
| Material 3 | via BOM |
| Navigation Compose | 2.8.5 |
| Hilt (Dagger) | 2.57.1 |
| KSP | 2.0.21-1.0.26 |
| Lottie Compose | 6.7.1 |
| OkHttp | 4.12.0 |
| Lifecycle Runtime KTX | 2.8.7 |
| Android Gradle Plugin | 8.7.3 |

---

## Arquitetura

O projeto adota a arquitetura **MVVM (Model-View-ViewModel)**, seguindo as recomendações oficiais do guia de arquitetura do Android. A separação de responsabilidades é organizada da seguinte forma:

```
View (Composable)  ──►  ViewModel  ──►  Repository / Manager
       ▲                    │
       └──── State / UI  ◄──┘
```

### Camadas

- **View** (`*View.kt`): Composables responsáveis exclusivamente pela UI. Observam o estado (`*State`) via `StateFlow` e disparam eventos para o ViewModel.
- **ViewModel** (`*ViewModel.kt`): Gerencia o estado da tela, aplica a lógica de negócio e emite eventos de navegação via `SharedFlow`. É injetado com Hilt (`@HiltViewModel`).
- **State** (`*State.kt`): Data class imutável que representa o estado atual da UI, consumida como `collectAsState()` nos Composables.
- **NavigationEvent**: Sealed class centralizada que desacopla as ações de navegação das telas, permitindo que a `AppNavigation` gerencie todas as rotas.

### Injeção de Dependências

Utilizando **Hilt**, as dependências são providas via módulo centralizado (`AppModule`), com escopo `@Singleton` para managers e repositories compartilhados.

---

## Estrutura do Projeto

```
app/src/main/java/com/example/composedevlab/
│
├── MainActivity.kt               # Entry point da aplicação
├── MyApplication.kt              # Classe Application anotada com @HiltAndroidApp
│
├── core/                         # Camada central reutilizável
│   ├── AppConstants.kt           # Constantes globais (chaves de storage, etc.)
│   ├── data/
│   │   ├── enums/                # Enumerações da aplicação (ex: AppTheme)
│   │   ├── extensions/           # Extension functions (ex: StringExtension)
│   │   ├── http/                 # Cliente HTTP com OkHttp
│   │   └── models/               # Modelos de dados (ResponseModel, LoginModel, etc.)
│   ├── di/
│   │   └── AppModule.kt          # Módulo Hilt com providers de dependências
│   └── shared/
│       ├── components/           # Componentes Compose reutilizáveis (ex: AppSearchBar)
│       ├── managers/             # Managers de estado global (AppManager, StorageManager)
│       └── repositories/        # Repositories compartilhados entre features
│
├── features/                     # Funcionalidades da aplicação
│   ├── Navigation.kt             # Grafo de navegação (NavHost + rotas)
│   ├── splash/                   # Tela de Splash
│   ├── onboarding/               # Fluxo de Onboarding
│   └── home/                     # Tela inicial / Home
│
└── ui/
    └── theme/                    # Tema da aplicação (Color, Type, Theme)
```

Cada feature segue o padrão:

```
features/{nome}/
├── {Nome}View.kt        # UI Composable (Route + Screen)
├── {Nome}ViewModel.kt   # Lógica e estado
└── {Nome}State.kt       # Estado imutável da tela
```

---

## Funcionalidades

### Implementadas

| Funcionalidade | Descrição | Status |
|---|---|---|
| **Splash Screen** | Tela inicial com timer de 3 segundos. Verifica via `AppManager` se o usuário já realizou o onboarding e redireciona para a rota correta. | ✅ Concluída |
| **Onboarding** | Fluxo de introdução com `HorizontalPager`, animações Lottie, indicadores de página animados e persistência da conclusão via `SharedPreferences`. | ✅ Concluída |
| **Home Page** | Tela central do app, ponto de acesso para todas as funcionalidades. Preparada para receber novos experimentos incrementalmente. | ✅ Em evolução |

### Planejadas / Em progresso

- [ ] Componentes customizados do Compose (campos, botões, cards)
- [ ] Animações e transições com `AnimatedContent` e `AnimatedVisibility`
- [ ] Testes de UI com Compose Testing
- [ ] Integração com APIs REST via OkHttp
- [ ] Gerenciamento de tema (claro/escuro/sistema) — base já implementada em `AppManager`
- [ ] Exploração de `LazyColumn`, `LazyGrid` e layouts avançados
- [ ] Gerenciamento de estado com `StateFlow`, `SharedFlow` e `Channel`
- [ ] Persistência local com Room

---

## Objetivo de Aprendizado

O projeto foi criado com os seguintes objetivos:

- **Praticar Kotlin** em cenários reais, explorando recursos modernos da linguagem (coroutines, flows, extension functions, sealed classes)
- **Aprofundar Jetpack Compose**, entendendo o ciclo de recomposição, gerenciamento de estado e construção de UIs declarativas
- **Testar diferentes abordagens arquiteturais**, consolidando o MVVM e explorando variações como UDF (Unidirectional Data Flow)
- **Manter um repositório vivo** de experimentação, onde cada funcionalidade documenta uma solução ou conceito explorado

---

## Como Executar

### Pré-requisitos

- Android Studio Ladybug (2024.2.x) ou superior
- JDK 17+
- Android SDK com API 26+

### Passos

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/ComposeDevLab.git

# Abra no Android Studio e sincronize o Gradle
# Execute em um emulador ou dispositivo físico
```

---

## Melhorias Futuras

- Adicionar tela de catálogo na Home com navegação para cada experimento
- Criar um sistema de tags por funcionalidade (ex: `#animation`, `#state`, `#network`)
- Implementar testes unitários para ViewModels
- Adicionar CI/CD com GitHub Actions
- Publicar builds de debug automaticamente via GitHub Releases

---

## Licença

Este projeto é de uso pessoal e educacional. Sinta-se à vontade para utilizá-lo como referência.
