;;; package -- Summary
;;; Commentary:
;;; Code:

(require 'package)
;; Include Marmalade Emacs List Package Repository and MELPA Stable (or not)
(add-to-list 'package-archives '("marmalade" . "http://marmalade-repo.org/packages/"))
;; (add-to-list 'package-archives '("melpa" . "http://melpa-stable.milkbox.net/packages/") t)
(add-to-list 'package-archives '("melpa" . "http://melpa.milkbox.net/packages/") t)
(package-initialize)

;; Load my personal elisp code
(add-to-list 'load-path "~/.emacs.d/mylibs")
(load-library "init_styles") ;; here be ourrr banners
(load-library "init_functionality") ;; here be keybindings and such
(load-library "init_autocompletion") ;; here be them that finish your sentences
(load-library "init_make_lint") ;; here be them that keep ye on the righteous path

(provide 'init)
;;; init.el ends here

