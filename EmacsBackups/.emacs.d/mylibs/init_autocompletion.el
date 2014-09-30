;;; package -- Load autocomplete packages
;;; Commentary:
;;; Code:

;; Enabling Autopair
(require 'autopair)
(autopair-global-mode 1)
(setq autopair-autowrap t)

;; Yasnippet (should be loaded before Auto-complete so they work together)
(require 'yasnippet)
(yas-global-mode 1)

;; Auto complete mode (should be loaded after Yasnippet so they can work together)
(require 'auto-complete-config)
(add-to-list 'ac-dictionary-directories "~/.emacs.d/ac-dict")
(ac-config-default)
;; Set the trigger key so that it can work together with Yasnippet on TAB key
;;  If the word exists in Yasnippet, pressing TAB will cause Yasnippet to
;; activate, otherwise, Auto-complete will
(ac-set-trigger-key "TAB")
(ac-set-trigger-key "<tab>")

;; Select candidates with C-n/C-p only when completion menu is displayed:
(setq ac-use-menu-map t)
(define-key ac-menu-map "C-n" 'ac-next)
(define-key ac-menu-map "C-p" 'ac-previous)

;; Enable Jedi mode for Python
(add-hook 'python-mode-hook 'jedi:setup)
(setq jedi:complete-on-dot t) ;; optional

;; Load Auto-complete Clang source (separately installed from packages)
(add-to-list 'load-path "~/.emacs.d/elpa/auto-complete-clang")

;; Load Auto-complete Clang
(require 'auto-complete-clang)
(global-set-key (kbd "C-+") 'ac-complete-clang)

(provide 'init_autocompletion)
;;; init_autocompletion ends here
