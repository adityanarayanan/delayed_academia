;;; package -- Summary
;;; Commentary:
;;; Code:

;; Load a different color scheme
;; (load-theme 'misterioso)
(load-theme 'adwaita)

(require 'package)
;; Include Marmalade Emacs List Package Repository and MELPA
(add-to-list 'package-archives '("marmalade" . "http://marmalade-repo.org/packages/"))
(add-to-list 'package-archives '("melpa" . "http://melpa.milkbox.net/packages/") t)
(package-initialize)

;; Check if el-get is installed and install if necessary
(add-to-list 'load-path "~/.emacs.d/el-get/el-get")

(unless (require 'el-get nil 'noerror)
  (with-current-buffer
      (url-retrieve-synchronously
       "https://raw.github.com/dimitri/el-get/master/el-get-install.el")
    (goto-char (point-max))
    (eval-print-last-sexp)))

(add-to-list 'el-get-recipe-path "~/.emacs.d/el-get-user/recipes")
(el-get 'sync)
;; End el-get code

;; Enabling IDO to supercharge buffer and file interactions
(require 'ido)
(ido-mode t)

;; Load Auto-complete Clang source
(add-to-list 'load-path "~/.emacs.d/elpa/auto-complete-clang")

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
;; If the word exists in Yasnippet, pressing TAB will cause Yasnippet to
;; activate, otherwise, Auto-complete will
(ac-set-trigger-key "TAB")
(ac-set-trigger-key "<tab>")

;; Load Auto-complete Clang
(require 'auto-complete-clang)
(global-set-key (kbd "C-+") 'ac-complete-clang)

;; Enable Jedi mode for Python
(add-hook 'python-mode-hook 'jedi:setup)
(setq jedi:complete-on-dot t) ;; optional

;; JavaScript mode settings
(require 'js2-refactor)
;; (js2r-add-keybindings-with-prefix "C-c m") ;; JS refactor command shortcuts
(add-to-list 'auto-mode-alist '("\\.js$" . js2-mode)) ;; associate all JS files with js2-mode
(add-to-list 'auto-mode-alist '("\\.json$" . js2-mode)) ;; to recognize json files
(add-hook 'js-mode-hook 'js2-minor-mode)
(add-hook 'js2-mode-hook 'ac-js2-mode)
(setq js2-highlight-level 3)

;; Permanently enable Flycheck
(add-hook 'after-init-hook #'global-flycheck-mode)

;; Set Flycheck to follow C++11 standard while checking c++ files
(add-hook 'c++-mode-hook (lambda () (setq flycheck-clang-language-standard "c++11")))

;; Remove default startup buffer
(setq inhibit-startup-message t)

;; Get rid of annoying ~ files
(setq make-backup-files nil)

;; Show line numbers and add space after the number
(global-linum-mode t)
(setq linum-format "%d ")
(custom-set-variables
 ;; custom-set-variables was added by Custom.
 ;; If you edit it by hand, you could mess it up, so be careful.
 ;; Your init file should contain only one such instance.
 ;; If there is more than one, they won't work right.
 '(custom-safe-themes (quote ("085b401decc10018d8ed2572f65c5ba96864486062c0a2391372223294f89460" "d677ef584c6dfc0697901a44b885cc18e206f05114c8a3b7fde674fce6180879" "8aebf25556399b58091e533e455dd50a6a9cba958cc4ebb0aab175863c25b9a4" "e16a771a13a202ee6e276d06098bc77f008b73bbac4d526f160faa2d76c1dd0e" "923faef2c7ed017e63f517703c846c6190c31400261e8abdb1be06d5b46ea19a" "dd4db38519d2ad7eb9e2f30bc03fba61a7af49a185edfd44e020aa5345e3dca7" default))))
(custom-set-faces
 ;; custom-set-faces was added by Custom.
 ;; If you edit it by hand, you could mess it up, so be careful.
 ;; Your init file should contain only one such instance.
 ;; If there is more than one, they won't work right.
 )

